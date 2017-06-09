package parser;

import java.util.ArrayList;
import java.util.List;

import model.Cuenta;
import model.Indicador;
import scala.collection.parallel.ParIterableLike.Foreach;

public class Termino {
	private int signo;
	private List<etiquetaCalculable> listaDeValores;
	public List<etiquetaCalculable> getListaDeValores() {
		return listaDeValores;
	}

	public List<operadorSecundario> getListaDeOperadores() {
		return listaDeOperadores;
	}

	private List<operadorSecundario> listaDeOperadores;
	
	public Termino(int signo, List<etiquetaCalculable> listaDeValores, List<operadorSecundario> listaDeOperadores) {
		this.signo = signo;
		this.listaDeValores = listaDeValores;
		this.listaDeOperadores = listaDeOperadores;
	}
	
	public double calcular(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		
		double acumulado = listaDeValores.get(0).obtenerValor(listaDeCuentas, listaDeIndicadores);
		int cont = 1;
		
		for(operadorSecundario operador : listaDeOperadores){
			operador.calcularOperacion(
					acumulado, 
					listaDeValores.get(cont),
					listaDeCuentas,
					listaDeIndicadores);
			cont++;
		}
		
		
		return acumulado * signo;
	}
	
	public boolean contieneEsteToken(TokenYTipo token){
		return listaDeValores
				.stream()
				.anyMatch(valor -> valor.obtenerEtiqueta().equals(token.getValor())
						);
	}
	
}
