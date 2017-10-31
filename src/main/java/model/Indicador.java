package model;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.uqbar.commons.utils.Observable;

import clasesResultantes.ResultadoIndicador;
import model.Arbol.Operaciones.Nodo;
import model.Excepciones.IdentificadorInexistente;
import model.Excepciones.RecursiveException;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeUsuario;

@Entity
@Table(name = "Indicador",uniqueConstraints= @UniqueConstraint(columnNames={"nombre", "usuario_id"}))
public class Indicador {
	public Usuario getUsuario() {
		return usuario;
	}

	//@SuppressWarnings("unused")
	protected Indicador() {}//Esto esta publico porque hibernate tiene problemitas
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	private String nombre;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Nodo arbol;
	
	
	@Override
	public String toString(){
		return nombre;
	}

	public Indicador(String nombre, Nodo arbol, String formula) {
		this.nombre = nombre;
		this.arbol = arbol;
		this.usuario = Repositorio.buscarPorId((long)1, Usuario.class);
	}
	
	public Indicador(String nombre, Nodo arbol, String formula, Usuario usuario) {
		this.nombre = nombre;
		this.arbol = arbol;
		this.usuario = usuario;
	}
	
	public Nodo getArbol(){
		return arbol;
	}

	private boolean esRecursivo(){
		return arbol.contieneEsteToken(nombre, usuario.getId());
	}
	public double calcularValor(List<Cuenta> listaDeCuentas) {
		if(esRecursivo()){
			throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			
		}

		return arbol.calcularValor(listaDeCuentas, RepositorioDeIndicadores.traerIndicadoresDeLaDB(usuario.getId()));
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
		return arbol.contieneEsteToken(token, usuario.getId());
	}
	
	public List<ResultadoIndicador> evaluarEmpresa(Empresa empresa){
		List<ResultadoIndicador> resultado = new ArrayList<>();
		empresa.getBalances().forEach(bal -> resultado.add(evaluarBalance(bal)));
		return resultado;
	}
	
	public ResultadoIndicador evaluarBalance(Balance bal) {
		double valor;
		try {
			valor = this.calcularValor(bal.getCuentas());
			return new ResultadoIndicador(bal.getAnio(), bal.getPeriodo(), Double.toString(valor));
		}
		catch(RecursiveException ex) {
			return new ResultadoIndicador(bal.getAnio(), bal.getPeriodo(), "No se puede calcular porque es recursivo");
		}
		catch(IdentificadorInexistente ex) {
			return new ResultadoIndicador(bal.getAnio(), bal.getPeriodo(), "No se pudo encontrar el valor: " + ex.getMessage());
		}
	}

}
