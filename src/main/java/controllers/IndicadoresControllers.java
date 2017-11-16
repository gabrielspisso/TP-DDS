package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Excepciones.ParserException;
import model.Excepciones.RecursiveException;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.http.matching.Halt;

public class IndicadoresControllers extends Controller{
	public  ModelAndView indicadores(Request req, Response res){	
		Map<String, Object> model = mapa(req);
		
		return new ModelAndView(model, "indicadores/indicadores.hbs");
	}
	
	@Override
	protected Map<String, Object> mapa(Request req){
		Map<String, Object> model = super.mapa(req);
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		System.out.println(id_usuario(req));
		model.put("indicadores", RepositorioDeIndicadores.traerIndicadoresDeLaDB(id_usuario(req)));
		return model;
	}
	
	//Es para que no usen los indicadores de alguien mas
	public void seguridad(String id_indicador, long id_usuario, Response res) {
		if(!RepositorioDeIndicadores.lePertenece(id_indicador, id_usuario)) {
			res.redirect("/404notFound");
		}
	}
	
	//Ya se, este metodo es re largo
	public ModelAndView mostrarResultados(Request req, Response res) {
		seguridad(req.params(":idIndicador"), id_usuario(req), res);
		Indicador indicador = Repositorio.buscarPorId(req.params(":idIndicador"), Indicador.class);
		Empresa empresa = Repositorio.buscarPorId(req.params(":idEmpresa"), Empresa.class);
		
		Map<String, Object> model = mapa(req);
		model.put("resultados", indicador.evaluarEmpresa(empresa));
		model.put("formula", indicador.mostrarFormulaCompleta());
		model.put("empresa", empresa.getNombre());
		return new ModelAndView(model, "indicadores/indicadoresConCalculos.hbs");
	}
	
	

	public ModelAndView formularioIndicador(Request req, Response res) {
		Map<String, Object> model = super.mapa(req);
		model.put("titulo", req.cookie("titulo"));
		model.put("tipoDeMensaje", req.cookie("tipoDeMensaje"));
		model.put("contenido", req.cookie("contenido"));
		model.put("formula", req.cookie("formula"));
		if(req.cookie("nuevo") != null) {
			res.removeCookie("nuevo");
			res.removeCookie("formula");
		}else {
			model.put("abierto", "collapse");
		}
		return new ModelAndView(model, "indicadores/crearIndicador.hbs");
	}
	
	
	public  Void recibirFormula(Request req, Response res){
		res.cookie("nuevo", "");
		String formula = req.queryParams("formula");
		Usuario usuario = Repositorio.buscarPorId(req.cookie("id"), Usuario.class);
		Indicador indicador; 
		String titulo = null, tipoDeMensaje = null, contenido = null;
		try {
			indicador = IndicadorBuilder.Build(formula + ";", usuario);
			RepositorioDeIndicadores.agregarIndicador(indicador);
			mensajeDeConfirmacion(res);
		}
		catch(RecursiveException ex) {
			mensajeDeError(res, formula, "alert-warning", "La definicion que usted ha ingresado es recursiva. Por favor intentelo de nuevo.");
		}
		catch(ParserException ex) {
			mensajeDeError(res, formula, "alert-danger", "La formula que ha ingresado no es lexicamente correcta. Por favor intentelo de nuevo.");
		}
		
		
		res.redirect("/indicadores/nuevo");
		return null;
	}
	
	private void mensajeDeConfirmacion(Response res) {
		res.cookie("titulo",  "El indicador se ha creado con exito!");
		res.cookie("tipoDeMensaje", "alert-success");
		res.cookie("contenido","El indicador ya se encuentra disponible para su uso. Seleccionelo desde la pestaña indicadores");
	}
	private void mensajeDeError(Response res, String formula, String color, String motivo) {
		res.cookie("titulo", "No se pudo crear el indicador");
		res.cookie("formula", formula );
		res.cookie("tipoDeMensaje", color);
		res.cookie("contenido", motivo);
	}
	
}
