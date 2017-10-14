package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Receiver;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Usuario;
import model.repositorioUsuariosEnClase;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController{
	
	
	public  ModelAndView home(Request req, Response res){
		
		Map<String, List<Usuario>> model = new HashMap<>();
		//Usuario user = repositorioUsuariosEnClase.lista().stream().filter(u ->u.getMail().equals(req.cookie("mail"))).findFirst().get();
		repositorioUsuariosEnClase repo = new repositorioUsuariosEnClase(); 
		model.put("users", repo.lista());
	//	model.put("frase", user.getFrase());
		
		return new ModelAndView(model, "home.hbs");
	}
	
	
	public  ModelAndView showLogin(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	
	public Void login(Request req, Response res){
		res.cookie("mail", req.queryParams("mail"));
		res.redirect("/home");
		return null;
	}
	
}
