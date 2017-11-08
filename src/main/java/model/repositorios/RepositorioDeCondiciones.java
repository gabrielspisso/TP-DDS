package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Condicion;

public class RepositorioDeCondiciones {

	public static void agregarCondicion(Condicion condicionAAgregar) {
		Repositorio.addInstanceToDB(Condicion.class, condicionAAgregar);
	}	
	
	public static List<Condicion> mostrarListaDeCondiciones() {
		return Repositorio.getFromDB(Condicion.class);
	}
	
	public static boolean existe(String nombre) {
		return Repositorio.existe(nombre, Condicion.class);
	}
	
	public static void borrar(String nombre) {
		Repositorio.borrar(nombre, Condicion.class);
	}

}
