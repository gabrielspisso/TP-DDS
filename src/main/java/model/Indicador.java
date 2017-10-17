package model;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import model.Arbol.Operaciones.NODO;
import model.Converter.ArbolConverter;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadores;

@Entity
@Observable
@Table(name = "Indicador")
public class Indicador {
	
	//@SuppressWarnings("unused")
	protected Indicador() {}//Esto esta publico porque hibernate tiene problemitas
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Convert(converter = ArbolConverter.class)
	private NODO arbol;
	
	
	@Override
	public String toString(){
		return nombre;
	}

	public Indicador(String nombre, NODO arbol, String formula) {
		this.nombre = nombre;
		this.arbol = arbol;
	}
	public NODO getArbol(){
		return arbol;
	}

	private boolean esRecursivo(){
		return arbol.contieneEsteToken(nombre);
	}
	public double calcularValor(List<Cuenta> listaDeCuentas) {
		if(esRecursivo()){
			throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			
		}

		return arbol.calcularValor(listaDeCuentas, RepositorioDeIndicadores.traerIndicadoresDeLaDB());
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
