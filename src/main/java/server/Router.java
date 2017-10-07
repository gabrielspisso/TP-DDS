package server;

import controllers.HomeController;
import controllers.ProyectosController;
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
		
		
		
		Spark.get("/", homeController::home, engine);
		Spark.get("/home", homeController::home, engine);
		Spark.get("/login", homeController::showLogin, engine);
		Spark.post("/login", homeController::login);
		
	//	Spark.get("/proyectos", proyectosController::listar, engine);
	//	Spark.get("/proyectos/new", proyectosController::nuevo, engine);
	//	Spark.get("/proyectos/:id", proyectosController::mostrar, engine);
	//	Spark.post("/proyectos", proyectosController::crear);
	}

}