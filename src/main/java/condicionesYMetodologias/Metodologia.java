package condicionesYMetodologias;

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
	public int evaluarMetodologia(Empresa empresa){
		Stream<Condicion> streamDeCondiciones =Condiciones.stream().filter(condicion -> condicion.cumpleCondicion(empresa));
		List<Condicion> condicionesFiltradas = streamDeCondiciones.collect(Collectors.toList());
		if(Condiciones.stream().anyMatch(x-> x.getTaxatividad()&& !x.cumpleCondicion(empresa))){
			throw new RuntimeException();
		}
		return condicionesFiltradas.size()*100/Condiciones.size();
	}
	public List<Empresa> listarEmpresas(List<Empresa> listaDeEmpresas){
		listaDeEmpresas.stream().filter(x-> sePuedeInvertir(x)); //Esto deberia hacerse de otra forma, pero no se me ocurre actualmente.
		Collections.sort(listaDeEmpresas,new Comparator<Empresa>() {
			public int compare(Empresa empresa1, Empresa empresa2) {
		        int res1 =  evaluarMetodologia(empresa1);
		        int res2 =  evaluarMetodologia(empresa2);
		        return res1 > res2 ? -1 : res1 == res2 ? 0 : 1;
		    }
		});
		return listaDeEmpresas;
	}
	private boolean sePuedeInvertir(Empresa empresa){
		try{
			evaluarMetodologia(empresa);
			return true;
		}
		catch(Exception ex){
			return false;
		}
		
	}
}
