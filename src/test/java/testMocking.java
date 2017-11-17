import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
 
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import model.Indicador;
import model.repositorios.Repositorio;
@RunWith(PowerMockRunner.class)
@PrepareForTest({Repositorio.class})
public class testMocking {

	@Test
	public void test() {
		
		PowerMockito.mockStatic(Repositorio.class);
		Indicador in1 = new Indicador("",null,"");
		when(Repositorio.buscarPorId(any(Long.class), eq(Indicador.class))).thenReturn(in1);

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
