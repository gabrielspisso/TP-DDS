package controllers;

import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresControllers extends Controller{
	public  ModelAndView indicadores(Request req, Response res){	
		Map<String, Object> model = mapa(req);
		
		return new ModelAndView(model, "indicadores/crearIndicadores.hbs");
	}
}
