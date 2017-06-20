package model;

import java.util.ArrayList;
import java.util.List;

import parser.SCANNER;
import parser.TokenYTipo;

public class IndicadorBuilder {
	
	
	private static Raiz nodoActual = null;
	private static List<Termino> listaDeTerminos;
	private static boolean raizTomada = false;
	
	public static Indicador Build(String expresion) {
    	List<TokenYTipo> lista = SCANNER.obtenerTokens(expresion);
    	String nombre = lista.get(0).getValor();
    	List<TokenYTipo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	listaDeTerminos = new ArrayList<>();
    	nodoActual = new Raiz(null, null, null);
    	listaDeTerminos.add(new Termino("+", nodoActual));
    	listaDeTokens.stream().forEach(token -> clasificarToken(token));
    	
    	
    	
		return new Indicador(nombre, listaDeTerminos);
	}
	
	private static void clasificarToken(TokenYTipo token) {
		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:{
			Nodo identificador = new IndicadorOCuenta(token.getValor());
			insertarHoja(identificador);
		}break;
			
		case OperadorPrimario:{
			nodoActual = new Raiz(null, null, null);
			listaDeTerminos.add(new Termino(token.getValor(), nodoActual));
		}break;
		case OperadorSecundario:{
			nodoActual.cargarOperacion(token.getValor());
		}break;
			
		case NUMERO:{
			Nodo numero = new Numero(token.getValor());
			insertarHoja(numero);
		}break;
		case FinDeLinea:break;
		default:
		}
	}

	private static void insertarHoja(Nodo hoja){
		if(nodoActual.getRamaDerecha() == null){
			nodoActual.setRamaDerecha(hoja);
		}
		else
			nodoActual.setRamaIzquierda(hoja);
			
	}
	
	private enum enNumero{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
}
