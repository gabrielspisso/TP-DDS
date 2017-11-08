import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clasesResultantes.ResultadoMetodologia;
import mockers.CargadorDeEmpresasMock;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.Calculos.Promedio;
import model.condicionesYMetodologias.Condicion;
import model.condicionesYMetodologias.CondicionConAño;
import model.condicionesYMetodologias.CondicionConComportamiento;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.condicionesYMetodologias.condicionConCalculo;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeUsuario;

public class testNuevasCondiciones {
	Usuario user;
	@Before
	public void cargarEmpresas(){
		List<Empresa> le = CargadorDeEmpresasMock.obtenerCuentasEmpresas();
		le.forEach(e -> RepositorioDeEmpresas.agregarEmpresas(e));
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		user = new Usuario("pipo", "");
	}	
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMayorA9(){

		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		condicionConCalculo test = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}
	
	@Test
	public void condicionConanioalgotieenequellegarasalir() {
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionConAño test = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}
	
	
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}

	@Test
	public void pruebaDeComprararDosEmpresasEmpresa1mayorAEmpresa2(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");

		CondicionEntreDosEmpresas test = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		
		assertTrue(test.cumpleLaCondicionEmpresarial(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0), Arrays.asList(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1))));
	}

	@Test
	public void pruebaDeComprararDosEmpresasEmpresa2mayorAEmpresa1(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");
		CondicionEntreDosEmpresas test = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		assertTrue(test.cumpleLaCondicionEmpresarial(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1), Arrays.asList(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0))));
	}
	
	@Test
	public void creandoUnaMetodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		condicionConCalculo conCalculo = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,"");
			
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(conCalculo), Arrays.asList(entreEmpresas), user);

		assertEquals("Metodologia Testenado",meto.getNombre());
	}
	
	@Test
	public void filtrandoCondicionesQueSiseAplicanaunaEmpresaDeunaMetodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		condicionConCalculo conCalculo = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,"");
			
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(conCalculo), Arrays.asList(entreEmpresas), user);

		assertEquals("Metodologia Testenado",meto.getNombre());
	}	

	@Test
	public void unaEmpresaTieneAplicaAl0lametsdfdsfsdodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		Empresa twitter = CargadorDeEmpresasMock.obtenerCuentasEmpresas().get(1);
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		condicionConCalculo conCalculo = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),10000,"");
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(conCalculo), Arrays.asList(), user);

		assertEquals(0,meto.sacarPorcentajeMetodologia(twitter));
	}	
	
	
	@Test
	public void unaEmpresaTieneAplicaAl100Porcientosdodologia1(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		CondicionConAño testanio = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(testanio), Arrays.asList(entreEmpresas), user);
		
		Empresa em1 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1);
		Empresa em2 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(2);
		
		meto.setTodasLasEmpresas(Arrays.asList(em1, em2));
	
		assertEquals(50,meto.sacarPorcentajeMetodologia(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}	
	
	@Test
	public void unaEmpresaTieneAplicaAl0Porcientosdodologia1(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		CondicionConAño testanio = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(testanio), Arrays.asList(), user);
		
		Empresa em1 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1);
		Empresa em2 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(2);
		
		meto.setTodasLasEmpresas(Arrays.asList(em1, em2));
	
		assertEquals(0,meto.sacarPorcentajeMetodologia(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}	
	
	
	@Test
	public void unaEmpresaTieneAplicaAl75Porcientosdodologia1(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Menor.getSingletonMenor(),2,"");
		
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		
		CondicionConAño testanio = new CondicionConAño(indicador,Menor.getSingletonMenor(),8,1,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(testanio, test), Arrays.asList(entreEmpresas), user);
		
		Empresa em0 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0);
		Empresa em1 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1);
		Empresa em2 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(2);
		
		meto.setTodasLasEmpresas(Arrays.asList(em0, em1, em2));
	
		assertEquals(33,meto.sacarPorcentajeMetodologia(em0));
	}	
	
	@Test
	public void TestFinalAplicarTalMEtodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		CondicionConAño testanio = new CondicionConAño(indicador,Menor.getSingletonMenor(),5,1,"");
		CondicionConAño esteCONDICIONTEstDADISTINTOALDEARRBA = new CondicionConAño(indicador,Mayor.getSingletonMayor(),5,1,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(testanio, test, test, esteCONDICIONTEstDADISTINTOALDEARRBA), Arrays.asList(entreEmpresas), user);

		Empresa em0 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0);
		Empresa em1 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1);
		Empresa em2 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(2);
		
		List<ResultadoMetodologia> resultado = meto.generarResultado(Arrays.asList(em0, em1, em2));
		
		assertEquals(25, (int) resultado.get(1).getValor());
	}	

	@Test
	public void TestFinalAplicarTalMEtodologiaRESULTADO2(){
		Indicador indicador = IndicadorBuilder.Build("cc=10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");

		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Menor.getSingletonMenor(),"nnombre");
		CondicionConAño testanio = new CondicionConAño(indicador,Menor.getSingletonMenor(),5,1,"");
		CondicionConAño esteCONDICIONTEstDADISTINTOALDEARRBA = new CondicionConAño(indicador,Mayor.getSingletonMayor(),5,1,"");
		
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(testanio, test, esteCONDICIONTEstDADISTINTOALDEARRBA), Arrays.asList(entreEmpresas), user);

		Empresa em0 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0);
		Empresa em1 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1);
		Empresa em2 = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(2);
	
		List<ResultadoMetodologia> resultado = meto.generarResultado(Arrays.asList(em0, em1, em2));
		
		assertEquals(25, (int) resultado.get(2).getValor());
	}
	
}