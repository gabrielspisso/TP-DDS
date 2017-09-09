package model;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Entity;
import org.uqbar.commons.utils.Observable;

import Excepciones.IdentificadorInexistente;
import model.Arbol.Operaciones.NODO;
import repositorios.RepositorioDeIndicadores;
@Entity
@Observable
public class Indicador {
	@Id
	@GeneratedValue
	private Long id;
	//@Unique
	private String nombre;
	@Transient
	private NODO arbol;
	private String formula;
	
	@Override
	public String toString(){
		return nombre;
	}

	public Indicador(String nombre, NODO arbol, String formula) {
		this.nombre = nombre;
		this.arbol = arbol;
		this.formula = formula;
	}


	private boolean esRecursivo(){
		return arbol.contieneEsteToken(nombre);
	}
	public double calcularValor(List<Cuenta> listaDeCuentas) {
		if(esRecursivo()){
			throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			
		}

		return arbol.calcularValor(listaDeCuentas, RepositorioDeIndicadores.getListaDeIndicadores());
	}
	public String mostrarFormula() {
		return arbol.mostrarFormula();
	}

	public String mostrarFormulaCompleta(){
		return nombre + " = " + mostrarFormula();
	}

	public String getNombre() {
		return nombre;
	}


	public boolean contieneEsteToken(String token) {
		return arbol.contieneEsteToken(token);
	}
	
	

}
