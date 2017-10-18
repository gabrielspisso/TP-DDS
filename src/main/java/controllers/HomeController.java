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

import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.condicionesYMetodologias.CondicionConAño;
import model.condicionesYMetodologias.Metodologia;
import model.condicionesYMetodologias.Resultado;
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

public class HomeController{
	
	
	public  ModelAndView home(Request req, Response res){	
		
		Map<String, Object> model = mapa(req);
		return new ModelAndView(model, "principal/main.hbs");
	}
	
	private Map<String, Object> mapa(Request req){
		Map<String, Object> model = new HashMap<>();
		Usuario user =Repositorio.buscarPorId(Long.valueOf(req.cookie("id")).longValue(),Usuario.class);
		model.put("nombreUsuario", user.getNombre());
		return model;
	}
	
	public  ModelAndView empresas(Request req, Response res){
		
		Map<String, Object> model = mapa(req);
		
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		
		return new ModelAndView(model, "empresas/verEmpresas.hbs");
	}
	
	public  ModelAndView indicadores(Request req, Response res){	
		
		Map<String, Object> model = mapa(req);
		
		return new ModelAndView(model, "indicadores/crearIndicadores.hbs");
	}
	
	List<Resultado> resultado;
	
	public  ModelAndView showMetodologias(Request req, Response res){		
		Map<String, Object> model = mapa(req);
		
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		
		model.put("metodologias", RepositorioDeMetodologias.traerMetodologiasDeLaDB());//--Habria que sacarlas del repo
		
		
		return new ModelAndView(model, "metodologias/listarMetodologias.hbs");
	}
	
	
	public  Void postMetodologias(Request req, Response res){		
		//Trae todas las empresas seleccionadas
		List<Empresa> empresasAEvaluar = RepositorioDeEmpresas.traerEmpresasDeLaDB()
				.stream()
				.filter(empresa -> req.queryParams("check" + empresa.getNombre() ) != null )
				.collect(Collectors.toList());
		
		
		Metodologia metodologia = Repositorio.buscarPorId(
				Long.valueOf(req.queryParams("metodologia")).longValue(), Metodologia.class);
		resultado = metodologia.generarResultado(empresasAEvaluar);
		
		res.cookie("nombreMetodologia", metodologia.getNombre());
		
		res.redirect("metodologias/resultado");
		return null;
	}
	
	public ModelAndView mostrarResultadoMetodologia(Request req, Response res) {
		Map<String, Object> model = mapa(req);		
		model.put("empresas", resultado);
		model.put("nombreMetodologia", req.cookie("nombreMetodologia"));
		return new ModelAndView(model, "metodologias/mostrarResultadoMetodologia.hbs");
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
			res.redirect("/main");
		}
		else
			res.redirect("/login");
		return null;
	}
	
}
