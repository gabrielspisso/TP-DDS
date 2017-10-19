package server;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresControllers;
import controllers.MetodologiasController;
import model.Usuario;
import model.repositorios.Repositorio;
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
				if(!req.pathInfo().equals("/login"))
					res.redirect("/login");
			}
			else {
				Usuario usuario = Repositorio.buscarPorId(Long.valueOf(id).longValue(),Usuario.class);
				if(usuario == null && !req.pathInfo().equals("/login") ) {
					res.redirect("/login");
				}
			}
		});
		
		Spark.get("/", homeController::home, engine);
		Spark.get("/main", homeController::home, engine);
			
		Spark.get("/empresas", empresaController::empresas, engine);
		
		Spark.get("/indicadores", indicadoresControllers::indicadores, engine);
		
		Spark.get("/metodologias", metodologiasController::showMetodologias, engine);
		
		//Spark.post("/metodologias", homeController::evaluarEmpresas);
		
		Spark.post("/metodologias", metodologiasController::postMetodologias);
		Spark.get("/metodologias/resultado", metodologiasController::mostrarResultadoMetodologia, engine);
		
		Spark.get("/login", homeController::showLogin, engine);
		Spark.post("/login", homeController::login);
		
		Spark.get("/logout", homeController::logout);

		
	//	Spark.get("/proyectos", proyectosController::listar, engine);
	//	Spark.get("/proyectos/new", proyectosController::nuevo, engine);
	//	Spark.get("/proyectos/:id", proyectosController::mostrar, engine);
	//	Spark.post("/proyectos", proyectosController::crear);
	}

}