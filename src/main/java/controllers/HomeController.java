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
import model.condicionesYMetodologias.CondicionConAño;
import model.condicionesYMetodologias.Metodologia;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends Controller{
	
	
	public  ModelAndView home(Request req, Response res){	
		
		Map<String, Object> model = mapa(req);
		return new ModelAndView(model, "principal/main.hbs");
	}

	
	public  ModelAndView showLogin(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	
	public  Void logout(Request req, Response res){
		res.removeCookie("id");
		res.redirect("/login");		
		return null;
	}
	
	public Void login(Request req, Response res){
		Usuario user = Repositorio.buscar(req.queryParams("nombre"), Usuario.class);
		if(user.getPassword().equals(req.queryParams("password"))) {
			res.cookie("id", user.getId().toString());	
			req.session(true);
			res.redirect("/main");
		}
		else
			res.redirect("/login");
		return null;
	}
	
}
