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
import model.repositorios.RepositorioDeEmpresasInterfaz;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeIndicadoresInterfaz;
import model.repositorios.RepositorioDeMetodologias;
import model.repositorios.RepositorioDeMetodologiasInterfaz;
import model.repositorios.RepositorioDeUsuarioInterfaz;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController extends Controller {

	
	List<ResultadoMetodologia> resultado;
	RepositorioDeMetodologiasInterfaz repoDeMetodologias;
	RepositorioDeEmpresasInterfaz repoDeEmpresas;
	RepositorioDeIndicadoresInterfaz repoIndicadores;
	
	
	public MetodologiasController(RepositorioDeEmpresasInterfaz repoDeEmpresas,
			RepositorioDeIndicadoresInterfaz repoIndicadores, RepositorioDeMetodologiasInterfaz repoDeMetodologias,RepositorioDeUsuarioInterfaz repoUsuario) {
		super(repoUsuario);
		this.repoDeEmpresas = repoDeEmpresas;
		this.repoIndicadores = repoIndicadores;
		this.repoDeMetodologias = repoDeMetodologias;
	}
	
	public  ModelAndView showMetodologias(Request req, Response res){		

		Map<String, Object> model = mapa(req);

		String clase = req.session().attribute("clase");
		if(clase == null) clase ="collapse";
		model.put("clase", clase);
		req.session().removeAttribute("clase");
		model.put("empresas", repoDeEmpresas.traerEmpresasDeLaDB());
		model.put("metodologias", repoDeMetodologias.traerMetodologiasDeLaDB( id_usuario(req)) );//--Habria que sacarlas del repo
		
		return new ModelAndView(model, "metodologias/listarMetodologias.hbs");
	}
	
	
	public Void postMetodologias(Request req, Response res){		
		List<Empresa> empresasAEvaluar = repoDeEmpresas.traerEmpresasDeLaDB()
				.stream()
				.filter(empresa -> req.queryParams("check" + empresa.getNombre() ) != null )
				.collect(Collectors.toList());
		req.session().attribute("empresasAEvaluar", empresasAEvaluar);
		req.session().attribute("idMetodologia", req.queryParams("metodologia"));
		res.redirect("metodologias/resultado");
		return null;
	}
	
	
	public ModelAndView mostrarResultadoMetodologia(Request req, Response res) {
		Map<String, Object> model = mapa(req);
		List<Empresa> empresas = req.session().attribute("empresasAEvaluar");
		Long idMetodologia = Long.valueOf(  req.session().attribute("idMetodologia")  ).longValue();
		
		Metodologia metodologia = repoDeMetodologias.buscarPorId(idMetodologia);
		
		if(noHayEmpresas(empresas)) {
			req.session().attribute("clase","");
			res.redirect("/metodologias");
			return null;
		}
		
		req.session().attribute("clase","collapse");
		model.put("empresas", metodologia.generarResultado(empresas,repoIndicadores));
		model.put("nombreMetodologia", metodologia.getNombre());
		
		req.session().removeAttribute("empresasAEvaluar");
		return new ModelAndView(model, "metodologias/mostrarResultadoMetodologia.hbs");
	}
	
	private boolean noHayEmpresas(List<Empresa> empresas) {
		return empresas==null ? true : empresas.isEmpty();
	}
	
	
	
}
