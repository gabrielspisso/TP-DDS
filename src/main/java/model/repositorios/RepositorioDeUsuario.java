package model.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Usuario;
import model.condicionesYMetodologias.Metodologia;

public class RepositorioDeUsuario {
		public static void agregarUsuario(Usuario usuario) {
		Repositorio.addInstanceToDB(Usuario.class,  usuario);
		 }
	
		 public static List<Usuario> traerMetodologiasDeLaDB() {
			 return Repositorio.getFromDB(Usuario.class);
		 }
	
		 public static boolean existe(String nombre) {
				return Repositorio.existe(nombre, Usuario.class);
		}
		 public static void borrar(String nombre) {
				Repositorio.borrar(nombre, Usuario.class);
			}
		 
}
