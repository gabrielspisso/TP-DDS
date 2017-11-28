package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeUsuarioInterfaz;
import spark.Request;

public abstract class Controller {
	RepositorioDeUsuarioInterfaz repoUsuario;
	public Controller(RepositorioDeUsuarioInterfaz repoUsuario) {
		super();
		this.repoUsuario = repoUsuario;
	}

	protected Map<String, Object> mapa(Request req){
		Map<String, Object> model = new HashMap<>();
		Usuario user =repoUsuario.buscarPorId(id_usuario(req).toString());
		model.put("nombreUsuario", user.getNombre());
		return model;
	}
	
	protected Long id_usuario(Request req) {
		if(req.cookie("id") == null) {
			return null;
		}
		return Long.valueOf(req.cookie("id")).longValue();
	}
	
}
