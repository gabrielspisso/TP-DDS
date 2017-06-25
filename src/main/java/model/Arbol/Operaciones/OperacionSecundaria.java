package model.Arbol.Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public class OperacionSecundaria implements Operacion{
	
	String operacion;
	protected Operacion izquierda;
	protected Operacion derecha;
	
	public OperacionSecundaria(String operacion, Operacion izquierda, Operacion derecha) {
		super();
		this.operacion = operacion;
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	@Override
	public boolean contieneEsteToken(String token) {
		return izquierda.contieneEsteToken(token) || derecha.contieneEsteToken(token);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return 0;
	}

	@Override
	public String mostrarFormula() {
		return izquierda.mostrarFormula() + operacion + derecha.mostrarFormula();
	}
}
