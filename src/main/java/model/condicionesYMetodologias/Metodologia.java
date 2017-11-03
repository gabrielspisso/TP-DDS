package model.condicionesYMetodologias;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import clasesResultantes.ResultadoMetodologia;
import model.Empresa;
import model.Usuario;
import model.repositorios.RepositorioDeEmpresas;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Metodologia",uniqueConstraints= @UniqueConstraint(columnNames={"nombre", "usuario_id"}))
public class Metodologia {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Usuario usuario;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<Condicion> condicionesFiltradoras;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<Condicion> condicionesOrdenadoras;
	
	@Transient
	private List<Empresa> todasLasEmpresas;
	
	String nombre;
	String descripcion;
	
	protected Metodologia() {}

	public Metodologia(String nombre,String Metodologia,List<Condicion> CondicionesFiltro, List<Condicion> CondicionesOrden, Usuario usuario){
		this.nombre = nombre;
		this.condicionesFiltradoras = CondicionesFiltro;
		this.condicionesOrdenadoras = CondicionesOrden;
		this.descripcion = Metodologia;
		this.usuario = usuario;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Long getId() {
		return id;
	}

	public String toString(){
		return nombre;
	}
	
	public String getDescripcion(){
		if(descripcion==null)
			descripcion="No hay descripcion";
		
		return descripcion;
	}

	private int cantidadCondicionesCumplieronFiltro(Empresa empresa) {
		if(condicionesFiltradoras.isEmpty())
			return 0;
		
		List<Condicion> condicionesQueCumplieron = condicionesFiltradoras.stream().filter(condicion -> (condicion).cumpleCondicion(empresa)).collect(Collectors.toList());
		return condicionesQueCumplieron.size();
	}
	
	private int cantidadCondicionesCumplieronFiltroEntreEmpresas(Empresa empresa) {
		if(condicionesOrdenadoras.isEmpty())
			return 0;
		
		List<Condicion> condicionesQueCumplenEntreEmpresas = condicionesOrdenadoras.stream().filter(condicion -> (condicion).cumpleLaCondicionEmpresarial(empresa, todasLasEmpresas)).collect(Collectors.toList());
		return condicionesQueCumplenEntreEmpresas.size();
	}
	
	//Deberia ser private , pero para los test
	public int sacarPorcentajeMetodologia(Empresa empresa) {
		int cant_condiciones = condicionesFiltradoras.size() + condicionesOrdenadoras.size();
		int cant_condicionesCumplidas = cantidadCondicionesCumplieronFiltro(empresa) + cantidadCondicionesCumplieronFiltroEntreEmpresas(empresa);
		
		return (cant_condicionesCumplidas*100)/(cant_condiciones);
	}
	
	public List<ResultadoMetodologia> generarResultado(List<Empresa> listaDeEmpresas){
		
		List<ResultadoMetodologia> resultado = new ArrayList<>();
		this.todasLasEmpresas = listaDeEmpresas;
		
		listaDeEmpresas.forEach(emp -> 
			resultado.add( 
					new ResultadoMetodologia(emp.getNombre(), this.sacarPorcentajeMetodologia(emp))
				));
		return resultado;
	}	
}
