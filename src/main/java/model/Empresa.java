package model;

import java.util.List;

import org.uqbar.commons.utils.Observable;


@Observable
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
	@Override
	public boolean equals(Object Objeto){
		try{
			Empresa empresa = (Empresa) Objeto;			
			return empresa.nombre.equals(this.toString());
		}
		catch(Exception ex){
			return false;
		}
		//return empresa.nombre == this.nombre;
		
	}
}
