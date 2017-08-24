package repositorios;

import java.util.ArrayList;
import java.util.List;

import model.Empresa;

public class RepositorioDeEmpresas {
	private static List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();
	
	public static void agregarEmpresas(List<Empresa> listaDeEmpresas2){
		listaDeEmpresas.removeIf(x-> listaDeEmpresas2.contains(x));
		listaDeEmpresas.addAll(listaDeEmpresas2);
	}
	public static List<Empresa> mostrarEmpresas(){
		List<Empresa> listaDeEmpresas2 = new ArrayList<Empresa>();
		listaDeEmpresas2.addAll(listaDeEmpresas);
		return listaDeEmpresas2;
	}
}
