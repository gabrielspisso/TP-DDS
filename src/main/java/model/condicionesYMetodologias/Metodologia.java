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
	
	public Long getId() {
		return id;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	String nombre;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<CondicionDeFiltrado> condicionesDeFiltrado;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<CondicionEntreDosEmpresas> condicionesDeOrdenamiento;
	
	String descripcion;
	
	protected Metodologia() {
	}

	public Metodologia(	String nombre,String Metodologia,List<CondicionDeFiltrado> CondicionesDeFiltrado,List<CondicionEntreDosEmpresas> CondicionesDeOrdenamiento, Usuario usuario){
		this.nombre = nombre;
		this.condicionesDeOrdenamiento = CondicionesDeOrdenamiento;
		this.condicionesDeFiltrado = CondicionesDeFiltrado;
		
		this.descripcion = Metodologia;
		this.usuario = usuario;
	}
	
	public String getNombre(){
		return nombre;
	}
	public String toString(){
		return nombre;
	}
	public String getDescripcion(){
		if(descripcion==null)
			descripcion="No hay descripcion";
		
		return descripcion;
	}
	public int evaluarMetodologia(Empresa empresa,Empresa empresa2){
				
		if(sacarPorcentajeMetodologia(empresa)>sacarPorcentajeMetodologia(empresa2))
			return -1;
		else if(sacarPorcentajeMetodologia(empresa) <sacarPorcentajeMetodologia(empresa2) )
			return 1;
		
		return ordenarEmpresasSegunMetodologia(empresa,empresa2);

	}
	private int ordenarEmpresasSegunMetodologia(Empresa empresa, Empresa empresa2) {
		// TODO Auto-generated method stub
		int i;
		CondicionEntreDosEmpresas condicion;
		for(i=0;i<condicionesDeOrdenamiento.size();i++){
			condicion = (CondicionEntreDosEmpresas) condicionesDeOrdenamiento.get(i);
			int comparacion = condicion.compararEmpresas(empresa,empresa2);
			if(comparacion!=0)
				return comparacion;
		}
		return 0;
	}

	//No deberia ser public, pero por los test
	public int sacarPorcentajeMetodologia(Empresa empresa) {
		
		Stream<CondicionDeFiltrado> streamDeCondiciones =condicionesDeFiltrado.stream().filter(condicion -> (condicion).cumpleCondicion(empresa));
		List<CondicionDeFiltrado> condicionesFiltradas = streamDeCondiciones.collect(Collectors.toList());
				
		return condicionesFiltradas.size()*100/condicionesDeFiltrado.size();
	}

	public List<Empresa> listarEmpresas(List<Empresa> listaDeEmpresas){
		ordenarListadeEmpresasDeAcuerdoALaEvaluacionDeMetodologias(listaDeEmpresas);
		return listaDeEmpresas;
	}
	private void ordenarListadeEmpresasDeAcuerdoALaEvaluacionDeMetodologias(List<Empresa> listaDeEmpresas){
		Collections.sort(listaDeEmpresas,new Comparator<Empresa>() {
			public int compare(Empresa empresa1, Empresa empresa2) {

				return evaluarMetodologia(empresa1,empresa2);
		    }
		});
	}
	
	public List<ResultadoMetodologia> generarResultado(List<Empresa> listaDeEmpresas){
		List<ResultadoMetodologia> resultado = new ArrayList<>();
		listaDeEmpresas = this.listarEmpresas(listaDeEmpresas);
		listaDeEmpresas.forEach(emp -> 
			resultado.add( 
					new ResultadoMetodologia(emp.getNombre(), this.sacarPorcentajeMetodologia(emp))
				));
		return resultado;
	}
	
	
}
