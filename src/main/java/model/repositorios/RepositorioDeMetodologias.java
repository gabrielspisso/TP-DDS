package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Metodologia;

public class RepositorioDeMetodologias extends Repositorio implements RepositorioDeMetodologiasInterfaz {
	//private static List<Metodologia> listaDeMetodologias = new ArrayList<>();

	
	/*public static List<Metodologia> getListaDeMetodologias() {
	*	return listaDeMetodologias;
	*}

	/*public static void agregarMetodologia(Metodologia metodologia) {
	*	listaDeMetodologias.removeIf(metodologia2-> metodologia2.getNombre().equals(metodologia.getNombre()));
	*	listaDeMetodologias.add(metodologia);
	}*/
	
		public  List<Metodologia>  traerMetodologiasDeLaDB(Long id) {		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from Metodologia i where i.usuario.id = :id", Metodologia.class)
					.setParameter("id", id)
					.getResultList();		
		}
		public  void agregarMetodologia(Metodologia metodologiaAAgregar) {
			this.addInstanceToDB(Metodologia.class, metodologiaAAgregar);
		
		 }
	
		 public  List<Metodologia> traerMetodologiasDeLaDB() {
			 return this.getFromDB(Metodologia.class);
		 }
		 
		 public  boolean existe(String nombre) {
				return this.existe(nombre, Metodologia.class);
		}
		 public  void borrar(String nombre) {
			 this.borrar(nombre, Metodologia.class);
			}

		public  boolean lePertenece(String id_metodologia, Long id_usuario) {
			if(id_metodologia == null) return false;
			Metodologia met = this.buscarPorId(id_metodologia, Metodologia.class);
			if(met == null) return false;
			return met.getUsuario().getId() == id_usuario;
		}
		public Metodologia buscarPorId(Long idMetodologia) {
			return buscarPorId(idMetodologia,Metodologia.class);
		}
		 
		 
}
