package model;

import java.util.List;

public  class Numero implements Nodo{
	protected String valor;
	public Numero(String token){
		this.valor = token;
	}
	
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return Double.parseDouble(valor);
	}

	@Override
	public String mostrarFormula() {
		return valor;
	}

	@Override
	public boolean contieneEsteToken(String string) {
		return valor.equals(string);
	}

}
