package model;

import java.util.List;

import org.uqbar.commons.utils.Observable;


public class Balance {
	private String periodo;
	private String anio;
	private List<Cuenta> cuentas;
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Balance(String anio,String periodo, List<Cuenta> cuentas) {
		this.anio = anio;
		this.periodo = periodo;
		this.cuentas = cuentas;
	}
	public  List<Cuenta>getCuentas() {
		return cuentas;
	}
	public void setCuentas( List<Cuenta>  cuentas) {
		this.cuentas = cuentas;
	}
	public String getAnio(){
		return anio;
	}
	@Override
	public String toString() {
		return this.getPeriodo() +" "+ this.getAnio();
	}

}
