package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.condicionesYMetodologias.Condicion;

public class RepositorioDeCondicionesMock {
	List<Condicion> listaDeCondiciones = new ArrayList<Condicion>();
	public void agregarCondicion(Condicion condicionAAgregar) {
		listaDeCondiciones.add(condicionAAgregar);
	}
	
	public List<Condicion> mostrarListaDeCondiciones() {
		return listaDeCondiciones;
	}
	public boolean existe(String nombre) {
		return listaDeCondiciones.stream().anyMatch(x-> x.toString().equals(nombre));
	}
	public void borrar(String nombre) {
		listaDeCondiciones.removeIf(x-> x.toString().equals(nombre));
	}
}
