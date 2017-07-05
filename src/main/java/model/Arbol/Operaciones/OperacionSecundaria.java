package model.Arbol.Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public class OperacionSecundaria implements Operacion{
	
	String operador;
	protected Operacion izquierda;
	protected Operacion derecha;
	
	public OperacionSecundaria(String operacion, Operacion izquierda, Operacion derecha) {
		super();
		this.operador = operacion;
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	@Override
	public boolean contieneEsteToken(String token) {
		return izquierda.contieneEsteToken(token) || derecha.contieneEsteToken(token);
	}

	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return 0;
	}
	
	@Override
	public String mostrarFormula() {
		return izquierda.mostrarFormula() + operador + derecha.mostrarFormula();
	}
}
