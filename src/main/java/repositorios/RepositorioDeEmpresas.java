package repositorios;

import java.util.ArrayList;
import java.util.List;

import model.Empresa;

public class RepositorioDeEmpresas {
	private static List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();
	
	public static void agregarEmpresas(List<Empresa> listaDeEmpresas2){
		listaDeEmpresas.addAll(listaDeEmpresas2);
	}
	public static List<Empresa> mostrarEmpresas(){
		return listaDeEmpresas;
	}
}
