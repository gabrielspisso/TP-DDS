package model.Builders;

import java.util.List;

import Excepciones.RecursiveException;
import model.Indicador;
import model.Arbol.Operaciones.NODO;
import parser.SCANNER;
import parser.NodoNoClasificado;

public class IndicadorBuilder {

	
	public static Indicador Build(String expresion) {
    	List<NODO> lista = SCANNER.obtenerNodos(expresion);
    	String nombre = lista.get(0).valor();
    	List<NODO> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	NodoBuilder nb = new NodoBuilder(listaDeTokens);
    	
    	NODO arbol = nb.Build();
    	Indicador indicador = new Indicador(nombre, arbol, expresion);
    	
		if(indicador.contieneEsteToken(nombre)){
			throw new RecursiveException("No se pudo crear ya que es recursivo");
		}
    	return indicador;
	}
	
	
	public static NODO buildTreeFromExpresion(String expresion) {
		List<NODO> lista = SCANNER.obtenerApartirDeExpresion(expresion);
		NODO arbol = new NodoBuilder(lista).Build();
		return arbol;
	}
}
