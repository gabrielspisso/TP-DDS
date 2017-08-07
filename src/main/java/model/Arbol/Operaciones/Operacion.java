package model.Arbol.Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public abstract class Operacion implements NODO{
	
	String operador;
	protected NODO izquierda;
	protected NODO derecha;
	
	public Operacion(String operador, NODO izquierda, NODO derecha) {
		super();
		this.operador = operador;
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	@Override
	public boolean estaCargado() {
		return izquierda != null && derecha != null;
	}
	
	public void setIzquierda(NODO izquierda) {
		this.izquierda = izquierda;
	}

	public void setDerecha(NODO derecha) {
		this.derecha = derecha;
	}


	@Override
	public boolean contieneEsteToken(String token) {
		return izquierda.contieneEsteToken(token) || derecha.contieneEsteToken(token);
	}

	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	
	
	@Override
	public String mostrarFormula() {
		return izquierda.mostrarFormula() + operador + derecha.mostrarFormula();
	}
}
