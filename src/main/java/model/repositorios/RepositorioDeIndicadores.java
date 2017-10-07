package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;


public class RepositorioDeIndicadores {
	//private static List<Indicador> listaDeIndicadores = new ArrayList<>();

	/*public static List<Indicador> getListaDeIndicadores() {
		return listaDeIndicadores;
	}*/
	
	public static List<Indicador> traerIndicadoresDeLaDB() {
		return Repositorio.getFromDB(Indicador.class);
	}

	
	public static void agregarIndicador(Indicador indicador) {
		Repositorio.addInstanceToDB(Indicador.class,  indicador);
	}
	
	 
	/*public static void agregarIndicador(Indicador indicador) {
		listaDeIndicadores.removeIf(ind-> ind.getNombre().equals(indicador.getNombre()));
		listaDeIndicadores.add(indicador);
	}*/
	public static boolean existe(String nombre) {
		return Repositorio.existe(nombre, Indicador.class);
	}
	public static void borrar(String nombre) {
		Repositorio.borrar(nombre, Indicador.class);
	}
}
