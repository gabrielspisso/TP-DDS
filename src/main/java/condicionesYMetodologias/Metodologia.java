package condicionesYMetodologias;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Empresa;
import repositorios.RepositorioDeEmpresas;

public class Metodologia {
	List<Condicion> Condiciones;
	public Metodologia(	List<Condicion> Condiciones){
		this.Condiciones = Condiciones;
	}
	
	public int evaluarMetodologia(Empresa empresa){
		Stream<Condicion> streamDeCondiciones =Condiciones.stream().filter(condicion -> condicion.cumpleCondicion(empresa));
		List<Condicion> condicionesFiltradas = streamDeCondiciones.collect(Collectors.toList());
		return condicionesFiltradas.size()*100/Condiciones.size();
	}
	public List<Empresa> listarEmpresas(List<Empresa> listaDeEmpresas){
		Collections.sort(listaDeEmpresas,new Comparator<Empresa>() {
			public int compare(Empresa empresa1, Empresa empresa2) {
		        int res1 =  evaluarMetodologia(empresa1);
		        int res2 =  evaluarMetodologia(empresa2);
		        return res1 > res2 ? -1 : res1 == res2 ? 0 : 1;
		    }
		});
		return listaDeEmpresas;
	}
}
