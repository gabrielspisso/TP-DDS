import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import controllers.IndicadoresControllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Map;

import model.CargadorDeEmpresas;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Builders.IndicadorBuilder;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeIndicadoresInterfaz;
import model.repositorios.RepositorioDeIndicadoresMockeado;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
@RunWith(PowerMockRunner.class)
@PrepareForTest({Repositorio.class,RepositorioDeIndicadores.class})
public class testMocking {
	RepositorioDeIndicadoresInterfaz repo = new RepositorioDeIndicadoresMockeado();
	@Test
	public void test() {
	/*	
		PowerMockito.mockStatic(Repositorio.class);
		PowerMockito.mockStatic(RepositorioDeIndicadores.class);

		Indicador in1 =  IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;",repo);
		Empresa em1 = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0);
		when(Repositorio.buscarPorId(any(Long.class), eq(Indicador.class))).thenReturn(in1);
	
		when(Repositorio.buscarPorId(any(Long.class), eq(Empresa.class))).thenReturn(em1);

		Request req = Mockito.mock(Request.class);
		when(req.params(":idIndicador")).thenReturn("");
		
		IndicadoresControllers controller = new IndicadoresControllers();
		Response res = Mockito.mock(Response.class);
		when(RepositorioDeIndicadores.lePertenece(anyString(), anyLong())).thenReturn(true);
		ModelAndView view = controller.mostrarResultados(req, res);
		Map<String, Object> model = (Map<String, Object>) view.getModel();
		assertEquals(model.get("resultados"),7);
*/
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
