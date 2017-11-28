package model.repositorios;

import java.util.List;

import model.Usuario;

public interface RepositorioDeUsuarioInterfaz {
	public  void agregarUsuario(Usuario usuario) ;
	
		 public  List<Usuario> traerMetodologiasDeLaDB();
	
		 public boolean existe(String nombre) ;
		 public void borrar(String nombre) ;

		public Usuario buscar(String queryParams);

		public Usuario buscarPorId(String cookie);
		 
}
