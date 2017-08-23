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
	public static List<Condicion> mostrarListaDeCondiciones(){
		List<Condicion> x = listaDeCondiciones;
		return x;
	}
}
