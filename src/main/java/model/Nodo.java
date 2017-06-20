package model;

import java.util.List;

public abstract class Nodo {
	protected String valorDelNodo;//Puede ser un numero, una cuenta o una operacion
	public Nodo(String valor) {
		super();
		this.valorDelNodo = valor;
	}
	public abstract boolean contieneEsteToken(String string);
	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	public abstract String mostrarFormula();
}
