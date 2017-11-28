package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class RepositorioDeUsuarioMockeado implements RepositorioDeUsuarioInterfaz{
	List<Usuario> usuarios = new ArrayList<Usuario>();
	public  void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	 }
	
		 public  List<Usuario> traerMetodologiasDeLaDB() {
			 return usuarios;
		 }
	
		 public  boolean existe(String nombre) {
				return  usuarios.stream().anyMatch(user-> user.getNombre().equals(nombre));
		}
		 public  void borrar(String nombre) {
				usuarios.removeIf(user-> user.getNombre().equals(nombre));
			}

		@Override
		public Usuario buscar(String nombre) {
			// TODO Auto-generated method stub
			if(usuarios.stream().anyMatch(user-> user.getNombre().equals(nombre))) {
				return usuarios.stream().filter(user-> user.getNombre().equals(nombre)).findFirst().get();
			}
			return  null;
		}

		@Override
		public Usuario buscarPorId(String cookie) {
			// TODO Auto-generated method stub
			if(usuarios.stream().anyMatch(usr->usr.getId() == Long.parseLong(cookie))) {
				return usuarios.stream().filter(usr->usr.getId() == Long.parseLong(cookie)).findFirst().get();
			}
			return  null;
		}
		 
}
