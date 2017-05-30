package parser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.io.LittleEndianDataOutputStream;

import model.Cuenta;


public class Indicador {
	String nombre;
	int valor;
	List<TokenYTipo> listaDeTokens;
	
	@Override
	public String toString(){
		return nombre;
	}
	
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
	
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		
		double valorAcumulado = 0;
		int indiceDelUltimoOperador = 0;
		int proximoSigno = 1;
		for (int i = 0; i < listaDeTokens.size(); i++) {
			if(esOperadorPrimario(i)){
				valorAcumulado += calcularTermino(
						listaDeTokens.subList(indiceDelUltimoOperador, i),
						listaDeCuentas, listaDeIndicadores)
						* proximoSigno;
				proximoSigno = valorDeOperacion(i);
				indiceDelUltimoOperador = i+1;
			}
			if(i == listaDeTokens.size()-1){
				valorAcumulado += calcularTermino(
						listaDeTokens.subList(indiceDelUltimoOperador, i+1),
						listaDeCuentas, listaDeIndicadores)
						* proximoSigno;
			}
		}
		
		return valorAcumulado;
	}
	
	private int valorDeOperacion(int i) {
		if(listaDeTokens.get(i).getValor().equals("+"))
			return 1;
		else
			return -1;
	}

	private boolean esOperadorPrimario(int index){
		return listaDeTokens.get(index).getTipo().equals("OperadorPrimario");
	}
	
	private double calcularTermino(List<TokenYTipo> lista, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		
		return calcular(0, 0, lista, listaDeCuentas,listaDeIndicadores);
	}
	
	private double calcular(int indice, double acumulado,List<TokenYTipo> listaDeTokens, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		
		TokenYTipo token = listaDeTokens.get(indice);
		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:
			acumulado = buscarValorDeIdentificador(token, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			acumulado = quedanTokens(indice, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			break;
		case NUMERO:
			acumulado = Double.parseDouble(token.getValor());
			acumulado = quedanTokens(indice, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			break;
		case OperadorSecundario:
			if(token.getValor().equals("/")) acumulado = acumulado / 
															calcular(indice+1,acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			if(token.getValor().equals("*")) acumulado = acumulado * 
															calcular(indice+1, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			break;
		case FinDeLinea:
			break;
		}
		return acumulado;
	}
	

	
	public double quedanTokens(int indice,double acumulado, List<TokenYTipo> listaDeTokens, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		if(listaDeTokens.size()-1 >indice+1)
			return calcular(indice +1, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
		return acumulado;
	}
	
	private double buscarValorDeIdentificador(TokenYTipo token, List<TokenYTipo> listaDeTokens, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarEnCuentas(token, listaDeIndicadores, listaDeCuentas);
		}
		catch(RuntimeException ex){
			
			try{
				return buscarEnIndicadores(token,listaDeTokens, listaDeIndicadores, listaDeCuentas);
			}
			
			catch(RuntimeException ex2){
				throw new RuntimeException("el valor "+ 
						token.getValor() +
						" no se encontro enlistado en las cuentas ni los indicadores");
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
	private double buscarEnIndicadores(TokenYTipo token,List<TokenYTipo> listaDeTokens, List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		
		try{
			indicador = listaDeIndicadores.stream()
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("No se encontro");
		}
		return indicador.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
	

	public enum enNumero{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
	

}
