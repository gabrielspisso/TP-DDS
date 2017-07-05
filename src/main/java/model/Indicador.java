package model;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import model.Builders.ArbolBuilder;
import repositorios.RepositorioDeIndicadores;
@Observable
public class Indicador {
	private String nombre;
	private List<Termino> terminos;
	
	@Override
	public String toString(){
		return nombre;
	}
	

	public Indicador(String nombre, List<Termino> listaDeTerminos) {
		this.nombre = nombre;
		this.terminos = listaDeTerminos;
	}


	private boolean esRecursivo(){
		return terminos.stream().anyMatch(termino -> termino.contieneEsteToken(nombre));
	}
	public double calcularValor(List<Cuenta> listaDeCuentas) {
		if(esRecursivo()){
			throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			
		}

		return terminos.stream().
				mapToDouble(
					termino -> termino.calcularValor(listaDeCuentas, RepositorioDeIndicadores.getListaDeIndicadores()))
				.sum();
	}

	private String formula;
	public String mostrarFormula() {
			formula = "";
		terminos.forEach(x-> formula += x.mostrarFormula());
		return formula;
	}

	public String mostrarFormulaCompleta(){
		return nombre + " = " + mostrarFormula();
	}

	public String getNombre() {
		return nombre;
	}


	public boolean contieneEsteToken(String token) {
		return terminos.stream().anyMatch(termino -> termino.contieneEsteToken(token));
	}
	
	

}
