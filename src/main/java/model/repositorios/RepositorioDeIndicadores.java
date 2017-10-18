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
	

	public static List<Indicador>  traerIndicadoresDeLaDB(Long id) {		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from Indicador i where i.usuario.id = :id", Indicador.class)
					.setParameter("id", id)
					.getResultList();		
	}
	
	public static void agregarIndicador(Indicador indicador) {
		Repositorio.addInstanceToDB(Indicador.class,  indicador);
	}
	

	public static boolean existe(String nombre) {
		return Repositorio.existe(nombre, Indicador.class);
	}
	public static void borrar(String nombre) {
		Repositorio.borrar(nombre, Indicador.class);
	}
}
