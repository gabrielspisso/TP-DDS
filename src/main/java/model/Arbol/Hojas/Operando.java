package model.Arbol.Hojas;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Nodo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Operando extends Nodo{
	protected Operando() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Operando(String valor) {
		this.valor = valor;
	}

	public String valor() {
		return valor;
	};
	@Override
	public boolean estaCargado() {
		return true;
	}
	@Override
	public boolean contieneEsteToken(String token, Long id) {
		return false;
	}
	
	@Override
	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	
	@Override
	public String mostrarFormula() {
		return valor;
	}

}
