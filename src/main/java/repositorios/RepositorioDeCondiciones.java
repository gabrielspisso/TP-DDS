package repositorios;

import java.util.ArrayList;
import java.util.List;

import condicionesYMetodologias.Condicion;
import model.Empresa;

public class RepositorioDeCondiciones {
	private static List<Condicion> listaDeCondiciones = new ArrayList<Condicion>();
	
	public static void agregarCondicion(List<Condicion> listaDeCondiciones2){
		listaDeCondiciones.removeIf(x-> listaDeCondiciones2.contains(x));
		listaDeCondiciones.addAll(listaDeCondiciones2);
	}
	
	/*
	 * 
	
	  public static List<Condicion> mostrarListaDeCondiciones(){
		
		return entityManager()
        .createQuery("from Condicion", Condicion.class) //
        .getResultList();;
	}
	  
	  
	  
	  
	 */
	public static List<Condicion> mostrarListaDeCondiciones(){
		List<Condicion> listaDeCondiciones2 = new ArrayList<Condicion>();
		listaDeCondiciones2.addAll(listaDeCondiciones);
		return listaDeCondiciones2;
	}
}
