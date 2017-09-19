package model.Arbol.Hojas;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.NODO;

public abstract class Operando implements NODO{
	protected String valor;

	public Operando(String valor) {
		this.valor = valor;
	}

	public String valor() {
		return valor;
	};
	@Override
	public boolean esOperacion() {
		return false;
	}
	@Override
	public boolean estaCargado() {
		return true;
	}
	@Override
	public boolean contieneEsteToken(String token) {
		return false;
	}
	
	@Override
	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	
	@Override
	public String mostrarFormula() {
		return valor;
	}

}
