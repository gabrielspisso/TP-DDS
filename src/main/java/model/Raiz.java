package model;

import java.util.List;

import Operaciones.Operacion;


public class Raiz extends Nodo{

	Nodo ramaDerecha = null;
	Nodo ramaIzquierda = null;
	public Nodo getRamaDerecha() {
		return ramaDerecha;
	}
	public void setRamaDerecha(Nodo ramaDerecha) {
		this.ramaDerecha = ramaDerecha;
	}
	public Nodo getRamaIzquierda() {
		return ramaIzquierda;
	}
	public void setRamaIzquierda(Nodo ramaIzquierda) {
		this.ramaIzquierda = ramaIzquierda;
	}

	public void setValorDeOperacion(String valor){
		this.valorDelNodo = valor;
	}
	
	public Raiz(String valor, Nodo ramaDerecha, Nodo ramaIzquierda) {
		super(valor);
		this.ramaDerecha = ramaDerecha;
		this.ramaIzquierda = ramaIzquierda;
	}
	@Override
	public boolean contieneEsteToken(String string) {
		return false;
	}
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		Operacion operacion = new Operacion(listaDeCuentas, listaDeIndicadores);
		return operacion.calcular(valorDelNodo, ramaIzquierda, ramaDerecha);
	}
	@Override
	public String mostrarFormula() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void cargarOperacion(String nuevaOperacion) {
		moverArbolActualALaRamaIzquierda(nuevaOperacion);
		
		valorDelNodo = nuevaOperacion;
		this.ramaDerecha = null;
	}

	
	private void moverArbolActualALaRamaIzquierda(String nuevaOperacion){
		Raiz nuevaRamaIzquierda = new Raiz(valorDelNodo, ramaDerecha, ramaIzquierda);
		this.ramaIzquierda = nuevaRamaIzquierda;
	}

	
	
}
