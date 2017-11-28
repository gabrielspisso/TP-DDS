package model.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Usuario;
import model.condicionesYMetodologias.Metodologia;

public class RepositorioDeUsuario extends Repositorio implements RepositorioDeUsuarioInterfaz  {
		public void agregarUsuario(Usuario usuario) {
			this.addInstanceToDB(Usuario.class,  usuario);
 }
	
		 public List<Usuario> traerMetodologiasDeLaDB() {
			 return this.getFromDB(Usuario.class);
		 }
	
		 public boolean existe(String nombre) {
				return this.existe(nombre, Usuario.class);
		}
		 public void borrar(String nombre) {
			 this.borrar(nombre, Usuario.class);
		}

		public Usuario buscarUsuario(String id) {
			buscarPorId(id, Usuario.class);
			return null;
		}

		@Override
		public Usuario buscar(String queryParams) {
			// TODO Auto-generated method stub
			return this.buscar(queryParams, Usuario.class);
		}

		@Override
		public Usuario buscarPorId(String cookie) {
			// TODO Auto-generated method stub
			return this.buscarPorId(cookie, Usuario.class);
		}
		 
}
