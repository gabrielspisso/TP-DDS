package model.Builders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Termino;
import model.Arbol.Operaciones.Operacion;
import parser.NodoNoClasificado;
public class ArbolBuilder {

	private static Queue<NodoNoClasificado> colaDeNodos;
	private static List<Termino> listaDeTerminos;
	private static Termino terminoActual;
	private static Operacion arbolActual;

	public static List<Termino> Build(List<NodoNoClasificado> lista){
		colaDeNodos = new LinkedList<>(lista);
		
		arbolActual = consumirHoja();
		terminoActual = new Termino("", arbolActual);
		
		listaDeTerminos = new ArrayList<>();
		listaDeTerminos.add(terminoActual);
		while(!colaDeNodos.isEmpty()){ //Deberia ser mas decente, con un foreach o algo.
			
			NodoNoClasificado nodo = colaDeNodos.poll();
			if(nodo.esOperacionPrimaria()){
				agregarUnNuevoTermino(nodo);
			}
			else{
				agregarOperacionSecundaria(nodo);
			}	
		}
		
		return listaDeTerminos;
	}
	
	private static void agregarOperacionSecundaria(NodoNoClasificado nodo) {
		arbolActual = nodo.convertirAOperacionSecundaria(arbolActual, consumirHoja());
		terminoActual.setArbol(arbolActual);
	}

	private static void agregarUnNuevoTermino(NodoNoClasificado nodo){
		arbolActual = consumirHoja();
		terminoActual = new Termino(nodo.getValor(), arbolActual);
		listaDeTerminos.add(terminoActual);
	}
	
	
	private static Operacion consumirHoja(){
		return colaDeNodos.poll().convertirEnHoja();
	}


}
