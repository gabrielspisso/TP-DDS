package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import clasesResultantes.ResultadoMetodologia;
import model.Empresa;
import model.condicionesYMetodologias.Metodologia;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController extends Controller {

	
List<ResultadoMetodologia> resultado;
	
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
	
	
	
}
