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

public class IndicadoresControllers extends Controller{
	public  ModelAndView indicadores(Request req, Response res){	
		Map<String, Object> model = mapa(req);
		
		return new ModelAndView(model, "indicadores/indicadores.hbs");
	}
	
	@Override
	protected Map<String, Object> mapa(Request req){
		Map<String, Object> model = super.mapa(req);
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		model.put("indicadores", RepositorioDeIndicadores.traerIndicadoresDeLaDB(id_usuario(req)));
		return model;
	}
	
	//Es para que no usen los indicadores de alguien mas
	public void seguridad(String id_indicador, long id_usuario, Response res) throws IOException {
		if(!RepositorioDeIndicadores.lePertenece(id_indicador, id_usuario)) {
			res.raw().sendError(404, "La pagina que busca no existe");
		}
	}
	
	//Ya se, este metodo es re largo
	public ModelAndView mostrarResultados(Request req, Response res) throws IOException {
		seguridad(req.params(":idIndicador"), id_usuario(req), res);
		//Long id_indicador = Long.valueOf(req.params(":idIndicador")).longValue();
		//Long id_empresa = Long.valueOf(req.params(":idEmpresa")).longValue();
		Indicador indicador = Repositorio.buscarPorId(req.params(":idIndicador"), Indicador.class);
		Empresa empresa = Repositorio.buscarPorId(req.params(":idEmpresa"), Empresa.class);
		
		Map<String, Object> model = mapa(req);
		model.put("resultados", indicador.evaluarEmpresa(empresa));
		model.put("formula", indicador.mostrarFormulaCompleta());
		model.put("empresa", empresa.getNombre());
		return new ModelAndView(model, "indicadores/indicadoresConCalculos.hbs");
	}
	
	
	public  Void recibirDatos(Request req, Response res){
		res.redirect("/indicadores/" + req.params(":idIndicador") + "/" + req.params(":idEmpresa"));
		return null;
	}
	//Hago esta voltereta porque sino me tira direcciones de memoria
	public  Void postIndicadores(Request req, Response res){
		res.redirect("/indicadores/" + req.queryParams("selIndicador") + "/" + req.params("selEmpresa"));
		return null;
	}
	
	public ModelAndView formularioIndicador(Request req, Response res) {
		Map<String, Object> model = super.mapa(req);
		model.put("titulo", req.cookie("titulo"));
		model.put("tipoDeMensaje", req.cookie("tipoDeMensaje"));
		model.put("contenido", req.cookie("contenido"));
		if(req.cookie("nuevo") != null) {
			res.removeCookie("nuevo");
		}else {
			model.put("abierto", "collapse");
		}
		return new ModelAndView(model, "indicadores/crearIndicador.hbs");
	}
	public  Void recibirFormula(Request req, Response res){
		res.cookie("nuevo", "");
		String formula = req.queryParams("formula") + ";";
		Usuario usuario = Repositorio.buscarPorId(req.cookie("id"), Usuario.class);
		Indicador indicador; 
		try {
			indicador = IndicadorBuilder.Build(formula, usuario);
			RepositorioDeIndicadores.agregarIndicador(indicador);
			res.cookie("titulo", "El indicador se ha creado con exito!");
			res.cookie("tipoDeMensaje", "alert-success");
			res.cookie("contenido", "El indicador ya se encuentra disponible para su uso. Seleccionelo desde la pestaña indicadores");
		}
		catch(RecursiveException ex) {
			res.cookie("titulo", "No se pudo crear el indicador");
			res.cookie("tipoDeMensaje", "alert-danger");
			res.cookie("contenido", "La definicion que usted ha ingresado es recursiva. Por favor intentelo de nuevo.");
		}
		catch(ParserException ex) {
			res.cookie("titulo", "No se pudo crear el indicador");
			res.cookie("tipoDeMensaje", "alert-danger");
			res.cookie("contenido", "La formula que ha ingresado no es lexicamente correcta. Por favor intentelo de nuevo.");
		}
		res.redirect("/indicadores/nuevo");
		return null;
	}
	
	
}
