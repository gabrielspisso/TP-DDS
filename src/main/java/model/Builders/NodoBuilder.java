package model.Builders;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Arbol.Operaciones.Nodo;
import model.Arbol.Operaciones.Operacion;

public class NodoBuilder {
	Queue<Nodo> colaDeOperandos;
	Queue<Nodo> colaDeOperadores;
	
	Nodo nodoActual = null;
	
	
	public NodoBuilder(List<Nodo> lista) {
		colaDeOperandos = new LinkedList<>();
		colaDeOperadores = new LinkedList<>();
		lista.forEach(nodo->clasificar(nodo));
	}
	
	public NodoBuilder(Queue<Nodo> colaDeOperandos, Queue<Nodo> colaDeOperaciones) {
		this.colaDeOperandos = colaDeOperandos;
		this.colaDeOperadores = colaDeOperaciones;
	}

	public Nodo Build() {
		if(colaDeOperandos.size() == 1)
			return colaDeOperandos.poll();
		while(!colaDeOperadores.isEmpty()) {
			
			
			Operacion operacion = (Operacion)(colaDeOperadores.poll());
			//Esto podria ser un switch
			if(operacion.prioridad() == 0) {
					acomodarOperacionSecundaria(operacion);
			}
			else {
				return bifurcarArbol(operacion);
			}
		}
		if(!((Operacion)nodoActual).estaCargado())//pregunto si no esta cargado, si paso es porque le falta un operando
			((Operacion)nodoActual).setDerecha(colaDeOperandos.poll());
		return nodoActual;
	}
	
	private Nodo bifurcarArbol(Operacion operacion) {
		Nodo nodoIzquierdo = (nodoActual == null) ? colaDeOperandos.poll() : nodoActual;
		operacion.setIzquierda(nodoIzquierdo);
		operacion.setDerecha(new NodoBuilder(colaDeOperandos, colaDeOperadores).Build());
		return operacion;
	}

	private void acomodarOperacionSecundaria(Operacion operacion) {
		if(nodoActual == null)
			operacion.cargar(colaDeOperandos.poll(), colaDeOperandos.poll());
		else
			operacion.cargar(nodoActual, colaDeOperandos.poll());
		nodoActual = operacion;
	}
	
	
	private void clasificar(Nodo nodo) {
		if(!nodo.estaCargado())
			colaDeOperadores.add(nodo);
		else
			colaDeOperandos.add(nodo);
	}
	
}
