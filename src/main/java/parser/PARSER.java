package parser;

import java.util.List;
import java.util.NoSuchElementException;

import model.Cuenta;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class PARSER {

private static String nombre= "";
private static List<TokenYTipo> listaDeTokens;
public static double calcularValor(String nombre2,List<TokenYTipo> lista,List<Cuenta> listaDeCuentas){
		
		nombre= nombre2;
		listaDeTokens = lista;
		double valorAcumulado = 0;
		int indiceDelUltimoOperador = 0;
		int proximoSigno = 1;
		for (int i = 0; i < listaDeTokens.size(); i++) {
			if(esOperadorPrimario(i)){
				valorAcumulado += calcularTermino(
						listaDeTokens.subList(indiceDelUltimoOperador, i),
						listaDeCuentas, RepositorioDeIndicadores.getListaDeIndicadores())
						* proximoSigno;
				proximoSigno = valorDeOperacion(i);
				indiceDelUltimoOperador = i+1;
			}
			if(i == listaDeTokens.size()-1){
				valorAcumulado += calcularTermino(
						listaDeTokens.subList(indiceDelUltimoOperador, i+1),
						listaDeCuentas, RepositorioDeIndicadores.getListaDeIndicadores())
						* proximoSigno;
			}
		}
		
		return valorAcumulado;
	}
	
	private static int valorDeOperacion(int i) {
		if(listaDeTokens.get(i).getValor().equals("+"))
			return 1;
		else
			return -1;
	}

	private static boolean esOperadorPrimario(int index){
		return listaDeTokens.get(index).getTipo().equals("OperadorPrimario");
	}
	
	private static double calcularTermino(List<TokenYTipo> lista, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		double acumulador = 0;
		for (int i = 0; i < lista.size(); i++) {
			if(esOperadorSecundario(i, lista)){
				acumulador = calcularOperacionSecundaria(acumulador, i, lista, listaDeCuentas, listaDeIndicadores);
				i++;
			}
			else
				acumulador += conseguirValorDeToken(lista.get(i), listaDeCuentas, listaDeIndicadores);
		}
		
		return acumulador;
	}
	
	private static double conseguirValorDeToken(TokenYTipo token, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:
			return 	buscarValorDeIdentificador(token, listaDeTokens, listaDeCuentas, listaDeIndicadores);
			//acumulado = quedanTokens(indice, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
		case NUMERO:
			return Double.parseDouble(token.getValor());
			//acumulado = quedanTokens(indice, acumulado, listaDeTokens, listaDeCuentas, listaDeIndicadores);
		case FinDeLinea:
			return 0;
		default:
			return 0;
		}
	}

	private static double calcularOperacionSecundaria(double acumulador, int indice, List<TokenYTipo> lista, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		if(lista.get(indice).getValor().equals("*"))
			return acumulador * conseguirValorDeToken(lista.get(indice+1), listaDeCuentas, listaDeIndicadores);
		else if(lista.get(indice).getValor().equals("/"))
			return acumulador / conseguirValorDeToken(lista.get(indice+1), listaDeCuentas, listaDeIndicadores);
		else
			return acumulador * (-1) * conseguirValorDeToken(lista.get(indice+1), listaDeCuentas, listaDeIndicadores);
	}
	
	private static boolean esOperadorSecundario(int index, List<TokenYTipo> lista){
		return lista.get(index).getTipo().equals("OperadorSecundario");
	}
	
	
	private static double buscarValorDeIdentificador(TokenYTipo token, List<TokenYTipo> listaDeTokens, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarEnCuentas(token, listaDeIndicadores, listaDeCuentas);
		}
		catch(NoSuchElementException ex){
			
			try{
				return buscarEnIndicadores(token,listaDeTokens, listaDeIndicadores, listaDeCuentas);
			}
			
			catch(RuntimeException ex2){
				throw new RuntimeException(ex2.getMessage());
			}
			
		}
	}
	
	private static double buscarEnCuentas(TokenYTipo token, List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Cuenta cuenta;
		cuenta = listaDeCuentas.stream()
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();

		return cuenta.getValor();
	}
	private static double buscarEnIndicadores(TokenYTipo token,List<TokenYTipo> listaDeTokens, List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		
		try{
			indicador = listaDeIndicadores.stream()
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();
			if(indicador.getListaDeTokens().stream().anyMatch(x-> x.getValor().equals(nombre)))
				throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("El valor "
					+"\""
					+token.getValor() 
					+"\" no se encontro en el listado de cuentas ni de indicadores");
		}
			
		return indicador.calcularValor(listaDeCuentas);
	}
	

	public enum enNumero{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
}
