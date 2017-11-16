package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "Cuenta")
public class Cuenta {
	
	@SuppressWarnings("unused")
	private Cuenta() {}
	
	@Id
	@GeneratedValue
	private Long id;
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
	@Override
	public boolean equals(Object Objeto){
		try{
			Cuenta cuenta = (Cuenta) Objeto;			
			return cuenta.getNombre().equals(this.getNombre());
		}
		catch(Exception ex){
			return false;
		}
	}
	public void actualizarCuenta(Balance balanceViejo,Balance balance) {
		Cuenta cuenta = balance.getCuentas().stream().filter(c-> c.equals(this)).findFirst().get();
		if(cuenta != null) {
			if(cuenta.getValor() != this.valor) {
				this.valor = cuenta.getValor();
				balanceViejo.limpiarIndicadoresPrecalculados(this);				
			}
		}
		else {
			balanceViejo.agregarCuenta(this);
		}
	}
	
	
}
