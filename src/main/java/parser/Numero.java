package parser;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public  class Numero implements etiquetaCalculable{
	protected String valor;
	public Numero(TokenYTipo token){
		this.valor = token.getValor();
	}
	
	@Override
	public double obtenerValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return Double.parseDouble(valor);
	}

	@Override
	public String obtenerEtiqueta() {
		return valor;
	}

}
