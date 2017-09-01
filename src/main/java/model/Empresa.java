package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

@Entity
@Observable
public class Empresa {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Transient
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
