package server;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresControllers;
import controllers.MetodologiasController;
import model.Usuario;
import model.Excepciones.ParserException;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeMetodologias;
import model.repositorios.RepositorioDeUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
	
		HomeController homeController = new HomeController(	new RepositorioDeUsuario());
		EmpresasController empresaController = new EmpresasController(new RepositorioDeUsuario(),	new RepositorioDeEmpresas());
		IndicadoresControllers indicadoresControllers = new IndicadoresControllers(new RepositorioDeEmpresas(),new RepositorioDeIndicadores(), new RepositorioDeUsuario());
		MetodologiasController metodologiasController = new MetodologiasController(new RepositorioDeEmpresas(),new RepositorioDeIndicadores(), new RepositorioDeMetodologias(),	new RepositorioDeUsuario());
		
		Spark.before((req,res)-> {
			String id = req.cookie("id");
			if (id == null) {
				if(Router.esPaginaPrivada(req))
					res.redirect("/login");
			}
			else {
				Usuario usuario = new RepositorioDeUsuario().buscarUsuario( id);
				if(usuario == null && Router.esPaginaPrivada(req)) {
					res.redirect("/login");
				}
			}
		});
		
		Spark.get("/", homeController::home, engine);
		Spark.get("/main", homeController::home, engine);
		Spark.get("/empresas", empresaController::empresas, engine);
		
		Spark.get("/metodologias", metodologiasController::showMetodologias, engine);
		Spark.post("/metodologias", metodologiasController::postMetodologias);//Envia al servidor las empresas seleccionadas, y la metodologia a usar
		Spark.get("/metodologias/resultado", metodologiasController::mostrarResultadoMetodologia, engine);
		
		Spark.get("/login", homeController::showLogin, engine);
		Spark.post("/login", homeController::login);
		Spark.put("/logout", homeController::logout);
		
		///Estos 2 son casi iguales
		Spark.get("/indicadores", indicadoresControllers::indicadores, engine);//Pantalla sin resultados
		Spark.get("/indicadores/:idIndicador/:idEmpresa", indicadoresControllers::mostrarResultados, engine); 

		Spark.get("/indicadores/nuevo", indicadoresControllers::formularioIndicador, engine);
		Spark.post("/indicadores/nuevo", indicadoresControllers::recibirFormula);
		
		Spark.get("*", Router::paginaDeError,engine);
		Spark.get("/404notFound", Router::paginaDeError,engine);
	}
	
	private static ModelAndView paginaDeError(Request req, Response res) {
		return new ModelAndView(null, "errorPage.hbs");
	}
	private static boolean esPaginaPrivada(Request req) {
		return !req.pathInfo().equals("/login");
	}

}