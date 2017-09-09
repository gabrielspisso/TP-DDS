package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//@Entity
public class Balance {
	/*@Id
	@GeneratedValue
	private Long id;
*/
	private String periodo;
	private int anio;
	//@OneToMany
	private List<Cuenta> cuentas;
	public String getPeriodo() {
		return periodo;
	}
	public Balance(int anio,String periodo, List<Cuenta> cuentas) {
		this.anio = anio;
		this.periodo = periodo;
		this.cuentas = cuentas;
	}
	public  List<Cuenta>getCuentas() {
		return cuentas;
	}

	public int getAnio(){
		return anio;
	}
	@Override
	public String toString() {
		return this.getPeriodo() +" "+ this.getAnio();
	}

}
