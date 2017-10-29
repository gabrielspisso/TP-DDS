package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Condicion;

public class RepositorioDeEmpresas {

	public static void agregarEmpresas(Empresa empresaAAgregar) {
		Repositorio.addInstanceToDB(Empresa.class, empresaAAgregar);
	}

	public static List<Empresa> traerEmpresasDeLaDB() {
		return Repositorio.getFromDB(Empresa.class);
	}

	public static boolean existe(String nombre) {
		return Repositorio.existe(nombre, Empresa.class);
	}
	public static void borrar(String nombre) {
		Repositorio.borrar(nombre, Empresa.class);
	}
	
}
