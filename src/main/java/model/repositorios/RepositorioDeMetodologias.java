package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Metodologia;

public class RepositorioDeMetodologias {
	//private static List<Metodologia> listaDeMetodologias = new ArrayList<>();

	
	/*public static List<Metodologia> getListaDeMetodologias() {
	*	return listaDeMetodologias;
	*}

	/*public static void agregarMetodologia(Metodologia metodologia) {
	*	listaDeMetodologias.removeIf(metodologia2-> metodologia2.getNombre().equals(metodologia.getNombre()));
	*	listaDeMetodologias.add(metodologia);
	}*/
	
		public static List<Metodologia>  traerMetodologiasDeLaDB(Long id) {		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from Metodologia i where i.usuario.id = :id", Metodologia.class)
					.setParameter("id", id)
					.getResultList();		
		}
		public static void agregarMetodologia(Metodologia metodologiaAAgregar) {
			Repositorio.addInstanceToDB(Metodologia.class,  metodologiaAAgregar);
		 }
	
		 public static List<Metodologia> traerMetodologiasDeLaDB() {
			 return Repositorio.getFromDB(Metodologia.class);
		 }
		 
		 public static boolean existe(String nombre) {
				return Repositorio.existe(nombre, Metodologia.class);
		}
		 public static void borrar(String nombre) {
				Repositorio.borrar(nombre, Metodologia.class);
			}

		public static boolean lePertenece(String id_metodologia, Long id_usuario) {
			if(id_metodologia == null) return false;
			Metodologia met = Repositorio.buscarPorId(id_metodologia, Metodologia.class);
			if(met == null) return false;
			return met.getUsuario().getId() == id_usuario;
		}
		 
		 
}
