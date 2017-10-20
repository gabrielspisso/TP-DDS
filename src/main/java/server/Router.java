package server;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresControllers;
import controllers.MetodologiasController;
import model.Usuario;
import model.Excepciones.ParserException;
import model.repositorios.Repositorio;
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
		
		HomeController homeController = new HomeController();
		EmpresasController empresaController = new EmpresasController();
		
		IndicadoresControllers indicadoresControllers = new IndicadoresControllers();
		
		MetodologiasController metodologiasController = new MetodologiasController();
		
		
		
		
		Spark.before((req,res)-> {
			String id = req.cookie("id");
			if (id == null) {
				if(Router.esPaginaPrivada(req))
					res.redirect("/login");
			}
			else {
				Usuario usuario = Repositorio.buscarPorId( id, Usuario.class);
				if(usuario == null && Router.esPaginaPrivada(req)) {
					res.redirect("/login");
				}
			}
		});
		
		Spark.get("/", homeController::home, engine);
		Spark.get("/main", homeController::home, engine);
			
		Spark.get("/empresas", empresaController::empresas, engine);
		
		
		
		
		Spark.get("/metodologias", metodologiasController::showMetodologias, engine);
		
		
		Spark.post("/metodologias", metodologiasController::postMetodologias);
		Spark.get("/metodologias/:idMetodologia/resultado", metodologiasController::mostrarResultadoMetodologia, engine);
		
		Spark.get("/login", homeController::showLogin, engine);
		Spark.post("/login", homeController::login);
		Spark.get("/logout", homeController::logout);

		
		///Estos 2 son casi iguales
		Spark.get("/indicadores", indicadoresControllers::indicadores, engine);//Pantalla sin resultados
		Spark.get("/indicadores/:idIndicador/:idEmpresa", indicadoresControllers::mostrarResultados, engine); 

		//Estos 2 son casi iguales
		Spark.post("/indicadores", indicadoresControllers::postIndicadores);//Pantalla sin resultados
		Spark.post("/indicadores/:idIndicador/:idEmpresa", indicadoresControllers::recibirDatos);
		
		Spark.get("/indicadores/nuevo", indicadoresControllers::formularioIndicador, engine);
		Spark.post("/indicadores/nuevo", indicadoresControllers::recibirFormula);
		
		//Spark.get("*", Router::paginaDeError,engine);
		Spark.get("/404notFound", Router::paginaDeError,engine);
		
	}
	
	private static ModelAndView paginaDeError(Request req, Response res) {
		
		return new ModelAndView(null, "errorPage.hbs");
	}
	private static boolean esPaginaPrivada(Request req) {
		return !req.pathInfo().equals("/login");
	}

}