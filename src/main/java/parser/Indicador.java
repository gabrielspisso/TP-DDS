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
	
	private void arreglarNombre(StringBuilder nombre){
		String aux = nombre.substring(nombre.toString().length()-1);
		//nombre = aux;
	}
	
	
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		List<TokenYTipo> listaNueva = new ArrayList<TokenYTipo>();
		int i = 0;
		while(i<listaDeTokens.size()-1){
			//Si el proximo token es una operacion secundaria, lo calculo
			if(elProximoTokenEsOperadorSecundario(i)){
				TokenYTipo nuevoToken = calcularOperacionSecundaria(i+1, listaDeCuentas, listaDeIndicadores);
				//Calculo el valor de la operacion y genero un nuevo token de tipo numero
				listaNueva.add(nuevoToken);
				i = i+3;
			}
			else{
				listaNueva.add(listaDeTokens.get(i));
				i++;
			}
		}
		
		return calcular(0,0, listaNueva, listaDeCuentas, listaDeIndicadores);
	}
	
	private boolean elProximoTokenEsOperadorSecundario(int indice){
		if(indice+1 < listaDeTokens.size())
			return listaDeTokens.get(indice+1).getTipo().equals("OperadorSecundario");
		return false;
	}
	
	private TokenYTipo calcularOperacionSecundaria(int indice, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		double valor;
		List<TokenYTipo> listaNueva = cortarLista(listaDeTokens, indice-1, indice+1);
		valor = calcular(0,0, listaNueva, listaDeCuentas, listaDeIndicadores);
		return new TokenYTipo("NUMERO", Double.toString(valor));
	}
	
	public List<TokenYTipo> cortarLista(List<TokenYTipo> lista, int inicio, int fin){
		List<TokenYTipo> listaNueva = new ArrayList<TokenYTipo>();
		for (int i = inicio; i <= fin; i++) {
			listaNueva.add(lista.get(i));
		}
		return listaNueva;
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
		case OperadorPrimario:
			if(token.getValor().equals("+")) acumulado = acumulado + calcular(indice+1, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			if(token.getValor().equals("-")) acumulado = acumulado - calcular(indice+1, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
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
		if(listaDeTokens.size()-1 >indice)
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
