package model;

import java.util.List;


public class Empresa {
	private String nombre;
	private List<Balance> balances;
	public List<Balance> getBalances() {
		return balances;
	}
	public Empresa(String nombre,List<Balance> balances) {
		this.nombre = nombre;
		this.balances = balances;
	}
	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}
	@Override
	public String toString() {
		return nombre;
	}
}
