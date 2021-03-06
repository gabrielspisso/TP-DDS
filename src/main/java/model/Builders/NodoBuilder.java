package model.Builders;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Arbol.Operaciones.*;

public class NodoBuilder {
	Queue<NODO> colaDeOperandos;
	Queue<NODO> colaDeOperadores;
	
	NODO nodoActual = null;
	
	
	public NodoBuilder(List<NODO> lista) {
		colaDeOperandos = new LinkedList<>();
		colaDeOperadores = new LinkedList<>();
		lista.forEach(nodo->clasificar(nodo));
	}
	
	public NodoBuilder(Queue<NODO> colaDeOperandos, Queue<NODO> colaDeOperaciones) {
		this.colaDeOperandos = colaDeOperandos;
		this.colaDeOperadores = colaDeOperaciones;
	}

	public NODO Build() {
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
		((Operacion)nodoActual).setDerecha(colaDeOperandos.poll());
		return nodoActual;
	}
	
	private NODO bifurcarArbol(Operacion operacion) {
		NODO nodoIzquierdo = (nodoActual == null) ? colaDeOperandos.poll() : nodoActual;
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
	
	
	private void clasificar(NODO nodo) {
		if(nodo.esOperacion())
			colaDeOperadores.add(nodo);
		else
			colaDeOperandos.add(nodo);
	}
	
}
