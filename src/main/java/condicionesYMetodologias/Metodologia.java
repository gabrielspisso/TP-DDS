package condicionesYMetodologias;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

import model.Empresa;
import repositorios.RepositorioDeEmpresas;


public class Metodologia {
	String nombre;
	List<Condicion> Condiciones;
	public Metodologia(	String nombre,List<Condicion> Condiciones){
		this.nombre = nombre;
		this.Condiciones = Condiciones;
	}
	
	public String getNombre(){
		return nombre;
	}
	public int evaluarMetodologia(Empresa empresa,Empresa empresa2){
				
		if(sacarPorcentajeMetodologia(empresa,empresa2)>sacarPorcentajeMetodologia(empresa2,empresa)){
			return -1;
		}
		else if(sacarPorcentajeMetodologia(empresa,empresa2) <sacarPorcentajeMetodologia(empresa2,empresa) ){
			return 1;
		}
		return 0;

	}
	//No deberia ser public, pero por los test
	public int sacarPorcentajeMetodologia(Empresa empresa, Empresa empresa2) {
		Stream<Condicion> streamDeCondiciones =Condiciones.stream().filter(condicion -> condicion.cumpleCondicion(empresa,empresa2));
		List<Condicion> condicionesFiltradas = streamDeCondiciones.collect(Collectors.toList());
		return condicionesFiltradas.size()*100/Condiciones.size();
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
