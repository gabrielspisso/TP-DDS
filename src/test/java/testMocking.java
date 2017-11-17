import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import static org.mockito.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito.*;
import org.mockito.*;
import org.powermock.*;
import org.powermock.core.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mock;

import org.mockito.Matchers.*;
import model.Empresa;
import model.IOs;
import model.CargadorDeEmpresas;
import model.Indicador;
import model.Builders.IndicadorBuilder;
import model.repositorios.Repo;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import org.powermock.api.mockito.PowerMockito;;
@RunWith(PowerMockRunner.class)
@PrepareForTest(Repositorio.class)
public class testMocking {

	@Test
	public void test() {
		
		PowerMockito.mockStatic(Repositorio.class);
		Indicador in1 = new Indicador("",null,"");
		BDDMockito.given(Repositorio.buscarPorId((long)2, Indicador.class)).willReturn(in1);
		Indicador in = Repositorio.buscarPorId((long)2, Indicador.class);
		
		
		fail("Not yet implemented");
	}
	
	
	/*
	@Test
	public ModelAndView mostrarResultados(Request req, Response res) {
		//seguridad(req.params(":idIndicador"), id_usuario(req), res);
		Indicador indicador = Repositorio.buscarPorId(req.params(":idIndicador"), Indicador.class);
		Empresa empresa = Repositorio.buscarPorId(req.params(":idEmpresa"), Empresa.class);
		
		//Map<String, Object> model = mapa(req);
		model.put("resultados", indicador.evaluarEmpresa(empresa));
		model.put("formula", indicador.mostrarFormulaCompleta());
		model.put("empresa", empresa.getNombre());
		return new ModelAndView(model, "indicadores/indicadoresConCalculos.hbs");
	}*/

}
