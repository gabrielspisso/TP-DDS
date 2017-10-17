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
import model.repositorioUsuariosEnClase;
import model.Builders.IndicadorBuilder;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeMetodologias;
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
		
		Map<String, Object> model = new HashMap<>();
	//	Usuario user = repositorioUsuariosEnClase.lista().stream().filter(u ->u.getMail().equals(req.cookie("mail"))).findFirst().get();
		repositorioUsuariosEnClase repo = new repositorioUsuariosEnClase(); 
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		
		return new ModelAndView(model, "empresas/verEmpresas.hbs");
	}
	
	public  ModelAndView indicadores(Request req, Response res){	
		
		/*
		 * Aca creo va toda la logica para ver si el flaco esta logueado o no
		 *  */
		return new ModelAndView(null, "indicadores/crearIndicadores.hbs");
	}
	
	List<Resultado> resultado;
	
	public  ModelAndView showMetodologias(Request req, Response res){		
		Map<String, Object> model = new HashMap<>();
		//	Usuario user = repositorioUsuariosEnClase.lista().stream().filter(u ->u.getMail().equals(req.cookie("mail"))).findFirst().get(); 
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
		
		
		Metodologia metodologiaAimplementar = Repositorio.buscarPorId(
				Long.valueOf(req.queryParams("metodologia")).longValue(), Metodologia.class);
		resultado = metodologiaAimplementar.generarResultado(empresasAEvaluar);

		res.redirect("metodologias/resultado");
		
		return null;
	}
	
	public ModelAndView mostrarResultadoMetodologia(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();		
		model.put("empresas", resultado);
		return new ModelAndView(model, "metodologias/mostrarResultadoMetodologia.hbs");
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
