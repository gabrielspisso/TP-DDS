package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import model.repositorios.Repositorio;
import spark.Request;

public abstract class Controller {
	protected Map<String, Object> mapa(Request req){
		Map<String, Object> model = new HashMap<>();
		Usuario user =Repositorio.buscarPorId(Long.valueOf(req.cookie("id")).longValue(),Usuario.class);
		model.put("nombreUsuario", user.getNombre());
		return model;
	}
}
