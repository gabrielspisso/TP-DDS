package model.Builders;

import java.util.List;

import model.Indicador;
import model.Usuario;
import model.Arbol.Operaciones.Nodo;
import model.Excepciones.RecursiveException;
import model.parser.NodoNoClasificado;
import model.parser.SCANNER;

public class IndicadorBuilder {

	
	public static Indicador Build(String expresion, Usuario usuario) {
    	List<Nodo> lista = SCANNER.obtenerNodos(expresion);
    	String nombre = lista.get(0).valor();
    	List<Nodo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	NodoBuilder nb = new NodoBuilder(listaDeTokens);
    	
    	Nodo arbol = nb.Build();
    	Indicador indicador = (usuario == null) ? new Indicador(nombre, arbol, expresion) : new Indicador(nombre, arbol, expresion, usuario);
    	if(usuario != null) {
    		if(indicador.contieneEsteToken(nombre)){
    			throw new RecursiveException("No se pudo crear ya que es recursivo");
    		}    		
    	}
    	return indicador;
	}
	
	public static Indicador Build(String expresion) {
		return Build(expresion, null);
	}
	
	public static Nodo buildTreeFromExpresion(String expresion) {
		List<Nodo> lista = SCANNER.obtenerApartirDeExpresion(expresion);
		Nodo arbol = new NodoBuilder(lista).Build();
		return arbol;
	}
}
