package model.Builders;

import java.util.List;

import model.Indicador;
import model.Arbol.Operaciones.Raiz;
import model.Excepciones.RecursiveException;
import model.parser.NodoNoClasificado;
import model.parser.SCANNER;

public class IndicadorBuilder {

	
	public static Indicador Build(String expresion) {
    	List<Raiz> lista = SCANNER.obtenerNodos(expresion);
    	String nombre = lista.get(0).valor();
    	List<Raiz> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	NodoBuilder nb = new NodoBuilder(listaDeTokens);
    	
    	Raiz arbol = nb.Build();
    	Indicador indicador = new Indicador(nombre, arbol, expresion);
    	
		if(indicador.contieneEsteToken(nombre)){
			throw new RecursiveException("No se pudo crear ya que es recursivo");
		}
    	return indicador;
	}
	
	
	public static Raiz buildTreeFromExpresion(String expresion) {
		List<Raiz> lista = SCANNER.obtenerApartirDeExpresion(expresion);
		Raiz arbol = new NodoBuilder(lista).Build();
		return arbol;
	}
}
