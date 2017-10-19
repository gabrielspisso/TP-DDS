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
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController extends Controller {

	
List<ResultadoMetodologia> resultado;
	
	public  ModelAndView showMetodologias(Request req, Response res){		
		Map<String, Object> model = mapa(req);

		String clase = req.session().attribute("clase");
		if(clase == null) clase ="collapse";
		model.put("clase", clase);
		req.session().removeAttribute("clase");
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		
		model.put("metodologias", RepositorioDeMetodologias.traerMetodologiasDeLaDB( id_usuario(req)) );//--Habria que sacarlas del repo
		
		return new ModelAndView(model, "metodologias/listarMetodologias.hbs");
	}
	
	
	public Void postMetodologias(Request req, Response res){		
		List<Empresa> empresasAEvaluar = RepositorioDeEmpresas.traerEmpresasDeLaDB()
				.stream()
				.filter(empresa -> req.queryParams("check" + empresa.getNombre() ) != null )
				.collect(Collectors.toList());
		
		req.session().attribute("empresasAEvaluar", empresasAEvaluar);
		res.redirect("metodologias/" + req.queryParams("metodologia") + "/resultado");
		return null;
	}
	
	private void seguridad(String id_metodologia, Request req, Response res) {
		
		if(!RepositorioDeMetodologias.lePertenece(id_metodologia, id_usuario(req))) {
			req.session().removeAttribute("empresasAEvaluar");
			res.redirect("/404notFound");
		}
	}
	
	public ModelAndView mostrarResultadoMetodologia(Request req, Response res) {
		seguridad(req.params(":idMetodologia"), req, res);
		Map<String, Object> model = mapa(req);
		List<Empresa> empresas = req.session().attribute("empresasAEvaluar");
		Metodologia metodologia = Repositorio.buscarPorId(req.params(":idMetodologia"), Metodologia.class);
		
		if(noHayEmpresas(empresas)) {
			req.session().attribute("clase","");
			res.redirect("/metodologias");
			return null;
		}
		
		req.session().attribute("clase","collapse");
		model.put("empresas", metodologia.generarResultado(empresas));
		model.put("nombreMetodologia", metodologia.getNombre());
		
		req.session().removeAttribute("empresasAEvaluar");
		return new ModelAndView(model, "metodologias/mostrarResultadoMetodologia.hbs");
	}
	
	private boolean noHayEmpresas(List<Empresa> empresas) {
		return empresas==null ? true : empresas.isEmpty();
	}
	
	
	
}
