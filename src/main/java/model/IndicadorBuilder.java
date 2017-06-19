package model;

import java.util.List;

import parser.SCANNER;
import parser.TokenYTipo;

public class IndicadorBuilder {
	
	private static Raiz arbol = null;
	private static Raiz nodoActual = null;
	
	public static Indicador Build(String expresion) {
    	List<TokenYTipo> lista = SCANNER.obtenerTokens(expresion);
    	String nombre = lista.get(0).getValor();
    	List<TokenYTipo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	nodoActual = new Raiz();
    	arbol = nodoActual;
    	
    	listaDeTokens.stream().forEach(token -> clasificarToken(token));
    	
    	
    	
		return new Indicador(nombre, arbol);
	}
	
	private static void clasificarToken(TokenYTipo token) {
		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:{
			arbol.cargarRama(new IndicadorOCuenta(token.getValor()));
		}break;
			
		case OperadorPrimario:{
			if(nodoActual.laOperacionEstaCargada()){
				Raiz nuevaRaiz = new Raiz();
				nuevaRaiz.cargarRama(arbol);
				arbol = nuevaRaiz;
			}
			arbol.cargarOperacion(token.getValor());
		}break;
		case OperadorSecundario:{
			if(nodoActual.laOperacionEstaCargada()){
				Raiz nuevaRaiz = new Raiz();
				nuevaRaiz.cargarRama(arbol);
				arbol = nuevaRaiz;
			}
			arbol.cargarOperacion(token.getValor());
		}break;
			
		case NUMERO:{
			arbol.cargarRama(new Numero(token.getValor()));
		}break;
		case FinDeLinea:
		default:
		}
	}
	
	
	private enum enNumero{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
}
