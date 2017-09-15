package repositorios;

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
		return Repositorio.getFromDB(Indicador.class, "Indicador");
	}

	
	public static void agregarIndicador(Indicador indicador) {
		Repositorio.addInstanceToDB(Indicador.class,  indicador, "Indicador");
	}
	
	 
	/*public static void agregarIndicador(Indicador indicador) {
		listaDeIndicadores.removeIf(ind-> ind.getNombre().equals(indicador.getNombre()));
		listaDeIndicadores.add(indicador);
	}*/
	
	
}
