package controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.midi.Receiver;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import clasesResultantes.ResultadoMetodologia;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.condicionesYMetodologias.CondicionConAnio;
import model.condicionesYMetodologias.Metodologia;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresasController extends Controller{
	public  ModelAndView empresas(Request req, Response res){
		
		Map<String, Object> model = mapa(req);
		
		model.put("empresas", RepositorioDeEmpresas.traerEmpresasDeLaDB());
		
		return new ModelAndView(model, "empresas/verEmpresas.hbs");
	}
}
