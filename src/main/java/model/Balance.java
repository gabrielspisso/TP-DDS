package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Balance")
public class Balance {
	
	@SuppressWarnings("unused")
	private Balance() {}
	
	@Id
	@GeneratedValue
	private Long id;

	private String periodo;
	private int anio;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "balance_id")
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
