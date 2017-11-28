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
import javax.persistence.OneToMany;
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
import model.repositorios.RepositorioDeIndicadoresInterfaz;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Nodo arbol;

	
	@Override
	public String toString(){
		return nombre;
	}

	
	
	public Indicador(String nombre, Nodo arbol, String formula, Usuario usuario) {
		this.nombre = nombre;
		this.arbol = arbol;
		this.usuario = usuario;
	}
	
	public Nodo getArbol(){
		return arbol;
	}

	private boolean esRecursivo(RepositorioDeIndicadoresInterfaz repo){
		return arbol.contieneEsteToken(nombre, usuario.getId(),repo);
	}
	public double calcularValor(List<Cuenta> listaDeCuentas,RepositorioDeIndicadoresInterfaz repo) {
		if(esRecursivo(repo)){
			throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			
		}

		return arbol.calcularValor(listaDeCuentas, repo.traerIndicadoresDeLaDB(usuario.getId()),repo);
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


	public boolean contieneEsteToken(String token,RepositorioDeIndicadoresInterfaz repo) {
		return arbol.contieneEsteToken(token, usuario.getId(),repo);
	}
	
	public List<ResultadoIndicador> evaluarEmpresa(Empresa empresa,RepositorioDeIndicadoresInterfaz repo){
		List<ResultadoIndicador> resultado = new ArrayList<>();
		empresa.getBalances().forEach(bal -> resultado.add(evaluarBalance(bal,repo)));
		return resultado;
	}
	
	public ResultadoIndicador evaluarBalance(Balance bal,RepositorioDeIndicadoresInterfaz repo) {
		if(bal.estaPrecalculadoElIndicador(this)){
			Double valor = bal.buscarEnListaDeResultados(this);
			return new ResultadoIndicador(bal.getAnio(), bal.getPeriodo(), Double.toString(valor));	
		}
		else{
			try {
				Double valor = this.calcularValor(bal.getCuentas(),repo);
				bal.agregarIndicadorPrecalculado(this,valor,repo);
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

/*	private Double buscarEnListaDeResultados(Balance bal, Empresa empresa) {
		Resultado res = listaDeResultados.stream().filter(x-> x.getAnio()== bal.getAnio() && x.getPeriodo().equals(bal.getPeriodo()) && x.getEmpresa().equals(empresa)).findFirst().get();
		return res.getResultado();
	}

	public void borrarPrecalculo(Cuenta cuenta, Empresa empresaDelArchivo, Balance balance) {
		listaDeResultados.removeIf(x-> x.getAnio() == balance.getAnio() 
									&& this.contieneEsteToken(cuenta.getNombre())
									&& balance.getPeriodo() == x.getPeriodo()
									&& empresaDelArchivo.equals(x.getEmpresa()));
		//return null;
	}
*/
}
