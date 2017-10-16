package condicionesYMetodologias;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.Empresa;
import repositorios.RepositorioDeEmpresas;


@Entity
@Table(name = "Metodologia")
public class Metodologia {
	
	@Id
	@GeneratedValue
	private Long id;
	
	String nombre;
	
	//@Transient
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<Condicion> CondicionesParaFiltrar;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<Condicion> CondicionesDeOrdenamiento;
	
	String descripcion;
	
	protected Metodologia() {
		super();
	}

	public Metodologia(	String nombre,String Metodologia,List<Condicion> Condiciones){
		this.nombre = nombre;
		this.CondicionesDeOrdenamiento = Condiciones.stream().filter(c->!c.esCondicionDeFiltrado()).collect(Collectors.toList());;;
		this.CondicionesParaFiltrar = Condiciones.stream().filter(c->c.esCondicionDeFiltrado()).collect(Collectors.toList());;;
		this.descripcion = Metodologia;
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
		for(i=0;i<CondicionesDeOrdenamiento.size();i++){
			condicion = (CondicionEntreDosEmpresas) CondicionesDeOrdenamiento.get(i);
			int comparacion = condicion.compararEmpresas(empresa,empresa2);
			if(comparacion!=0)
				return comparacion;
		}
		return 0;
	}

	//No deberia ser public, pero por los test
	public int sacarPorcentajeMetodologia(Empresa empresa) {
		Stream<Condicion> streamDeCondiciones =CondicionesParaFiltrar.stream().filter(condicion -> ((CondicionDeFiltrado) condicion).cumpleCondicion(empresa));
		List<Condicion> condicionesFiltradas = streamDeCondiciones.collect(Collectors.toList());
				
		return condicionesFiltradas.size()*100/CondicionesParaFiltrar.size();
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
	
}
