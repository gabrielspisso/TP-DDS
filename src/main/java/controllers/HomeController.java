package controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.midi.Receiver;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import clasesResultantes.ResultadoMetodologia;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.condicionesYMetodologias.CondicionConAnio;
import model.condicionesYMetodologias.Metodologia;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeMetodologias;
import model.repositorios.RepositorioDeUsuarioInterfaz;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends Controller{
	
	public HomeController(RepositorioDeUsuarioInterfaz repoUsuario) {
		super(repoUsuario);
		// TODO Auto-generated constructor stub
	}

	
	public  ModelAndView home(Request req, Response res){	
		
		Map<String, Object> model = mapa(req);
		return new ModelAndView(model, "principal/main.hbs");
	}

	
	public  ModelAndView showLogin(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		
		String collapse = req.cookie("abierto") == null ? "collapse" : req.cookie("abierto");
		model.put("abierto", collapse);
		res.removeCookie("abierto");
		return new ModelAndView(model, "login.hbs");
	}
	
	public  Void logout(Request req, Response res){
		res.removeCookie("id");
		res.redirect("/login");		
		return null;
	}
	
	public Void login(Request req, Response res){
		Usuario user = repoUsuario.buscar(req.queryParams("nombre"));
		
		if (user == null) {
			res.cookie("abierto", "");
			res.redirect("/login");
		}
		else if(user.getPassword().equals(req.queryParams("password"))) {
			res.cookie("id", user.getId().toString());	
			req.session(true);
			res.cookie("abierto", "collapse");
			res.redirect("/main");
		}
		else {
			res.cookie("abierto", "");
			res.redirect("/login");		
		}
		return null;
	}
	
	private  boolean esPaginaPrivada(Request req) {
		return !req.pathInfo().equals("/login");
	}
	
	private void chequearId(Request req, Response res) {
		String id = req.cookie("id");
		if (id == null) {
			if(this.esPaginaPrivada(req)) {
				res.redirect("/login");
			}
		}
		else {
			Usuario usuario = repoUsuario.buscarPorId( id);
			if(usuario == null && this.esPaginaPrivada(req)) {
				res.redirect("/login");
			}
		}
	}
	
}
