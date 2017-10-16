package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Receiver;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Empresa;
import model.Usuario;
import model.repositorioUsuariosEnClase;
import model.repositorios.RepositorioDeEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController{
	
	
	public  ModelAndView home(Request req, Response res){	
		
		/*
		 * Aca creo va toda la logica para ver si el flaco esta logueado o no
		 *  */
		return new ModelAndView(null, "principal/main.hbs");
	}
	
	
	
	public  ModelAndView empresas(Request req, Response res){
		
		Map<String, List<Empresa>> model = new HashMap<>();
	//	Usuario user = repositorioUsuariosEnClase.lista().stream().filter(u ->u.getMail().equals(req.cookie("mail"))).findFirst().get();
		repositorioUsuariosEnClase repo = new repositorioUsuariosEnClase(); 
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
	//	model.put("frase", user.getFrase());
		
		return new ModelAndView(model, "empresas/verEmpresas.hbs");
	}
	
	public  ModelAndView indicadores(Request req, Response res){	
		
		/*
		 * Aca creo va toda la logica para ver si el flaco esta logueado o no
		 *  */
		return new ModelAndView(null, "indicadores/crearIndicadores.hbs");
	}
	
	public  ModelAndView listarMetodologias(Request req, Response res){		
		/*
		 * Aca creo va toda la logica para ver si el flaco esta logueado o no
		 *  */
		return new ModelAndView(null, "metodologias/listarMetodologias.hbs");
	}
	
	public  ModelAndView mostrarResultadoMetodologia(Request req, Response res){		
		/*
		 * Aca creo va toda la logica para ver si el flaco esta logueado o no
		 *  */
		return new ModelAndView(null, "metodologias/mostrarResultadoMetodologia.hbs");
	}
	
	
	public  ModelAndView showLogin(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	
	public Void login(Request req, Response res){
		res.cookie("mail", req.queryParams("mail"));
		res.redirect("/main");
		return null;
	}
	
}
