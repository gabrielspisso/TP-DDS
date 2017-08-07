package model.Arbol.Hojas;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.NODO;

public abstract class Hoja implements NODO{
	protected String valorDeHoja;

	public Hoja(String valorDeHoja) {
		this.valorDeHoja = valorDeHoja;
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
		return valorDeHoja;
	}

}
