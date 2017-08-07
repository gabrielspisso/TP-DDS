package model.Builders;

import java.util.List;

import model.Arbol.Operaciones.NODO;
import model.Builders.OperacionSecundariaBuilder.direccion;
import parser.NodoNoClasificado;

public class parentesisBuilder {

	private static List<NODO> listaDeNodos;
	private static List<NODO> listaDeOperacionesPrimarias;
	private static NODO nodoActual;
	
	public static List<NODO> Build(List<NodoNoClasificado> lista){
		
		return listaDeOperacionesPrimarias;
	}

	/*
	private static void acomodarEnLista(NODO nodo) {
		if(nodo.estaCargado()) 
			ordenar(nodo); 
		else {
			listaDeOperacionesPrimarias.add(nodoActual);
			listaDeOperacionesPrimarias.add(nodo);
			ADondeVoy = direccion.inicio;
		}
	}*/
	
}
