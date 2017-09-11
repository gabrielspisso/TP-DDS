package model.Builders;

import java.util.List;

import Excepciones.RecursiveException;
import model.Indicador;
import model.Arbol.Operaciones.NODO;
import parser.SCANNER;
import parser.NodoNoClasificado;

public class IndicadorBuilder {

	
	public static Indicador Build(String expresion) {
    	List<NodoNoClasificado> lista = SCANNER.obtenerTokens(expresion);
    	String nombre = lista.get(0).getValor();
    	List<NodoNoClasificado> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	NODO arbol = OperacionPrimariaBuilder.Build(listaDeTokens);
    	Indicador indicador = new Indicador(nombre, arbol, expresion);
    	
		if(indicador.contieneEsteToken(nombre)){
			throw new RecursiveException("No se pudo crear ya que es recursivo");
		}
    	return indicador;
	}
	
	public static NODO buildTreeFromExpresion(String expresion) {
		List<NodoNoClasificado> lista = SCANNER.obtenerTokens("unNombre =" + expresion + ";");
		List<NodoNoClasificado> listaDeTokens =lista.subList(2, lista.size()-1);
		NODO arbol = OperacionPrimariaBuilder.Build(listaDeTokens);
		return arbol;
	}
}
