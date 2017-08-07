package model.Builders;

import java.util.List;

import model.Arbol.Operaciones.NODO;
import model.Arbol.Operaciones.Operacion;
import model.Builders.OperacionSecundariaBuilder.direccion;
import parser.NodoNoClasificado;

public class OperacionPrimariaBuilder {

	private static direccion ADondeVoy;
	private static List<NODO> lista;
	private static NODO nodoActual;
	
	public static NODO Build(List<NodoNoClasificado> listaDeNodos) {
		lista = OperacionSecundariaBuilder.Build(listaDeNodos);
		ADondeVoy = direccion.inicio;
		
		lista.forEach(nodo -> ordenar(nodo));
		
		return nodoActual;
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
	
	
}
