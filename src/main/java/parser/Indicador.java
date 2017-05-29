package parser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Cuenta;


public class Indicador {
	String nombre;
	int valor;
	List<TokenYTipo> listaDeTokens;
	
	public Indicador(List<TokenYTipo> listaDeTokens, String nombre) { //Falta agregar la de cuentas
		this.listaDeTokens = listaDeTokens;
		this.nombre = nombre.toString();
	}
	
	public List<TokenYTipo> getListaDeTokens() {
		return listaDeTokens;
	}

	public void recibirToken(TokenYTipo token){
		listaDeTokens.add(token);
	}
	
	public String getNombre(){
		return nombre;
	}
	
	private void arreglarNombre(StringBuilder nombre){
		String aux = nombre.substring(nombre.toString().length()-1);
		//nombre = aux;
	}
	
	private double getValor(){
		double valor = 0;
		
		return valor;
	}
	
	
	
	public double calcular(int indice, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		
		double acumulado = 0;
		TokenYTipo token = listaDeTokens.get(indice);

		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:
			acumulado += buscarValorDeIdentificador(token, listaDeCuentas, listaDeIndicadores) + quedanTokens(indice, listaDeCuentas, listaDeIndicadores);
			break;
		case NUMERO:
			acumulado += Double.parseDouble(token.getValor()) + quedanTokens(indice, listaDeCuentas, listaDeIndicadores);
			break;
		case OperadorPrimario:
			if(token.getValor().equals("+")) acumulado = acumulado + calcular(indice+1, listaDeCuentas, listaDeIndicadores);
			if(token.getValor().equals("-")) acumulado = acumulado - calcular(indice+1, listaDeCuentas, listaDeIndicadores);
			break;
		case OperadorSecundario:
			if(token.getValor().equals("/")) acumulado = acumulado / calcular(indice+1, listaDeCuentas, listaDeIndicadores);
			if(token.getValor().equals("*")) acumulado = acumulado * calcular(indice+1, listaDeCuentas, listaDeIndicadores);
			break;
		case FinDeLinea:
			break;
		}
		return acumulado;
	}
	
	public double quedanTokens(int indice, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		if(listaDeTokens.size()-1 >indice)
			return calcular(indice +1, listaDeCuentas, listaDeIndicadores);
		return 0;
	}
	
	private double buscarValorDeIdentificador(TokenYTipo token, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarEnCuentas(token, listaDeIndicadores, listaDeCuentas);
		}
		catch(RuntimeException ex){
			
			try{
				return buscarEnIndicadores(token, listaDeIndicadores, listaDeCuentas);
			}
			
			catch(RuntimeException ex2){
				throw new RuntimeException("el valor "+ 
						token.getValor() +
						"no se encontro enlistado en las cuentas ni los indicadores");
			}
			
		}
	}
	
	private double buscarEnCuentas(TokenYTipo token, List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Cuenta cuenta;
		try{
		cuenta = listaDeCuentas.stream()
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("No se encontro");
		}
		return cuenta.getValor();
	}
	private double buscarEnIndicadores(TokenYTipo token, List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		try{
			indicador = listaDeIndicadores.stream()
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("No se encontro");
		}
		return indicador.calcular(0, listaDeCuentas, listaDeIndicadores);
	}
	

	public enum enNumero{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
	

}
