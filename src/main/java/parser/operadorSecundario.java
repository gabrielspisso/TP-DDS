package parser;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public class operadorSecundario {
	private String operacion;
	
	public operadorSecundario(TokenYTipo token){
		this.operacion = token.getValor();
	}
	
	public double calcularOperacion(double acumulado, etiquetaCalculable proximo, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		oper enumval = oper.valueOf(this.operacion);
		switch(enumval){
		case por:
			return acumulado * proximo.obtenerValor(listaDeCuentas, listaDeIndicadores);
		case dividivo:
			return acumulado / proximo.obtenerValor(listaDeCuentas, listaDeIndicadores);
		case porMenos:
			return acumulado* (-1) * proximo.obtenerValor(listaDeCuentas, listaDeIndicadores);
		default:
			return 0;
		}
	}
	
	private enum oper{
		porMenos,
		por,
		dividivo
	}
	
}
