package model;

import java.util.List;

public  class Numero extends Nodo{

	public Numero(String valor) {
		super(valor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return Double.parseDouble(valorDelNodo);
	}

	@Override
	public String mostrarFormula() {
		return valorDelNodo;
	}

	@Override
	public boolean contieneEsteToken(String string) {
		return valorDelNodo.equals(string);
	}

}
