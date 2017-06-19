package model;

import java.util.List;

import parser.SCANNER;
import parser.TokenYTipo;

public class IndicadorBuilder {
	
	private static Raiz arbol = null;
	private static Raiz nodoActual = null;
	private static boolean esElPrimerElemento = true;
	
	public static Indicador Build(String expresion) {
    	List<TokenYTipo> lista = SCANNER.obtenerTokens(expresion);
    	String nombre = lista.get(0).getValor();
    	List<TokenYTipo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	nodoActual = new Raiz(null, null, null);
    	arbol = nodoActual;
    	
    	listaDeTokens.stream().forEach(token -> clasificarToken(token));
    	
    	
    	
		return new Indicador(nombre, arbol);
	}
	
	private static void clasificarToken(TokenYTipo token) {
		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:{
			nodoActual.cargarValor(new IndicadorOCuenta(token.getValor()));
			esElPrimerElemento = false;
		}break;
			
		case OperadorPrimario:{
			if(!esElPrimerElemento)
				nodoActual = arbol.cargarOperacionPrimaria(token.getValor());
			else
				arbol.cargarOperacion(token.getValor());
			
		}break;
		case OperadorSecundario:{
			nodoActual = nodoActual.cargarOperacionSecundaria(token.getValor());
		}break;
			
		case NUMERO:{
			nodoActual.cargarValor(new Numero(token.getValor()));
			esElPrimerElemento = false;
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
