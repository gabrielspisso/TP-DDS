package model.Builders;

import java.util.List;

import model.Indicador;
import model.Usuario;
import model.Arbol.Operaciones.Nodo;
import model.Excepciones.RecursiveException;
import model.parser.NodoNoClasificado;
import model.parser.SCANNER;
import model.repositorios.RepositorioDeIndicadoresInterfaz;

public class IndicadorBuilder {

	
	public static Indicador Build(String expresion, Usuario usuario,RepositorioDeIndicadoresInterfaz repo) {
    	List<Nodo> lista = SCANNER.obtenerNodos(expresion);
    	String nombre = lista.get(0).valor();
    	List<Nodo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	NodoBuilder nb = new NodoBuilder(listaDeTokens);
    	
    	Nodo arbol = nb.Build();
    	if(usuario == null) {
    		throw new RuntimeException("Usuario inexistente para crear indicador");
    	}
    	Indicador indicador =  new Indicador(nombre, arbol, expresion, usuario);

    		if(indicador.contieneEsteToken(nombre,repo)){
    			throw new RecursiveException("No se pudo crear ya que es recursivo");
    		}    		
    	return indicador;
	}
	
	public static Indicador Build(String expresion,RepositorioDeIndicadoresInterfaz repo) {
		
		return Build(expresion, new Usuario("",""),repo);
		
	}
	
	public static Nodo buildTreeFromExpresion(String expresion) {
		List<Nodo> lista = SCANNER.obtenerApartirDeExpresion(expresion);
		Nodo arbol = new NodoBuilder(lista).Build();
		return arbol;
	}
}
