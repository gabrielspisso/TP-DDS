package model.Builders;

import java.util.ArrayList;
import java.util.List;

import model.Arbol.Operaciones.NODO;
import model.Arbol.Operaciones.Operacion;
import parser.NodoNoClasificado;
public class OperacionSecundariaBuilder {

	private static List<NODO> listaDeNodos;
	private static List<NODO> listaDeOperacionesPrimarias;
	private static NODO nodoActual;
	
	
	private static direccion ADondeVoy;

	public static List<NODO> Build(List<NodoNoClasificado> lista){
		listaDeNodos = new ArrayList<>();
		listaDeOperacionesPrimarias = new ArrayList<>();
		ADondeVoy = direccion.inicio;
		
		lista.forEach(NodoNoClas -> listaDeNodos.add(convertirANodo(NodoNoClas)) );

		listaDeNodos.forEach(nodo -> acomodarEnLista(nodo));
		listaDeOperacionesPrimarias.add(nodoActual);//Para agregar el ultimo nodo que se leyo
		
		return listaDeOperacionesPrimarias;
	}
	
	private static NODO convertirANodo(NodoNoClasificado nodo) {
		return nodo.esOperacion() ? nodo.convertirAOperacion() : nodo.convertirEnHoja();
	}
	
	
	private static void acomodarEnLista(NODO nodo) {
		if(nodo.estaCargado()) 
			ordenar(nodo); 
		else {
			listaDeOperacionesPrimarias.add(nodoActual);
			listaDeOperacionesPrimarias.add(nodo);
			ADondeVoy = direccion.inicio;
		}
	}
	
	
	private static void ordenar(NODO nodo) {
		switch(ADondeVoy) {
			case inicio:{
				nodoActual = nodo;
				ADondeVoy = direccion.izquierda;
			}break;
			
			case izquierda:{
				((Operacion)nodo).setIzquierda(nodoActual);
				nodoActual = nodo;
				ADondeVoy = direccion.derecha;
			}break;
			
			case derecha:{
				((Operacion)nodoActual).setDerecha(nodo);
				ADondeVoy = direccion.izquierda;
			}break;
		}
	}


	enum direccion{
		inicio,
		izquierda,
		derecha,
	}

}
