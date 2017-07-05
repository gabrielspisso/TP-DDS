package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	private String nombre;
	private int valor;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getValor() {
		return valor;
	}
	public Cuenta(String nombre, int valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return this.getNombre();
	}

}
