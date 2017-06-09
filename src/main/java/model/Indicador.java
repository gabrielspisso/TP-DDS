package model;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ranges.RangeException;

import parser.PARSER;
import parser.SCANNER;
import parser.Termino;
import parser.TokenYTipo;


public class Indicador {
	private String nombre;
	private List<Termino> listaDeTerminos; 
	
	@Override
	public String toString(){
		return nombre;
	}
	
	
	public Indicador(String nombre, List<Termino> listaDeTerminos) {
		this.nombre = nombre;
		this.listaDeTerminos = listaDeTerminos;
	}

	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		//Si alguien encuentra como hacer un SUM en Java, lo deja mas lindo

		return listaDeTerminos
			.stream()
			.mapToDouble(termino -> termino.calcular(listaDeCuentas, listaDeIndicadores))
			.sum();
	}

	public String mostrarFormula() {
		/*String formula = "";
		List<String> l = new ArrayList<>();
		
		listaDeTokens.stream().forEach(x->l.add(x.getValor()));
		formula = String.join(" ", l );

		return nombre + " = " + formula;*/
		return "Puto el que lee";
	}


	public String getNombre() {
		return nombre;
	}


	public boolean contieneEsteToken(TokenYTipo token) {
		return listaDeTerminos
				.stream()
				.anyMatch(termino -> termino.contieneEsteToken(token)
						);
	}
	
	

}
