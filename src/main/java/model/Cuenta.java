package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

@Observable
//@Entity
public class Cuenta {
/*	@Id
	@GeneratedValue
	private Long id;
*/	private String nombre;
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
