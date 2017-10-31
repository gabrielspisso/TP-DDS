package model.Arbol.Operaciones;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Indicador;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Nodo")
public abstract class Nodo {
	protected Nodo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue
	protected Long id;
	
	public Long getId() {
		return id;
	}
	protected String valor;
	
	public abstract boolean contieneEsteToken(String token, Long id);
	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	public abstract String mostrarFormula();
	public abstract boolean estaCargado();
	public abstract String valor();
}
