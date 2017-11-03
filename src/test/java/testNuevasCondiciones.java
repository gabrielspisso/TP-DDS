import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		List<Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada();
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
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionEntreDosEmpresas test = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		
		assertTrue(test.cumpleLaCondicionEmpresarial(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0), Arrays.asList(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(1))));
	}

	@Test
	public void pruebaDeComprararDosEmpresasEmpresa2mayorAEmpresa1(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

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
	public void unaEmpresaTieneAplicaAl00lametsdfdsfsdodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		Empresa twitter = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(1);
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		condicionConCalculo conCalculo = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,"");
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(conCalculo), Arrays.asList(entreEmpresas), user);

		assertEquals(0,meto.sacarPorcentajeMetodologia(twitter));
	}	
	
	
	@Test
	public void unaEmpresaTieneAplicaAlPorcientosdodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(test), Arrays.asList(entreEmpresas), user);

		assertEquals(100,meto.sacarPorcentajeMetodologia(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}	
	
	@Test
	public void unaEmpresaTieneAplicaAl75Porcientosdodologia(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionConComportamiento test2 = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionConComportamiento test3 = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionConComportamiento test4 = new CondicionConComportamiento(indicador,Menor.getSingletonMenor(),2,"");
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		
		Metodologia meto = new Metodologia("Metodologia Testenado", null, Arrays.asList(test, test2, test3, test4), Arrays.asList(entreEmpresas), user);

		assertEquals(75,meto.sacarPorcentajeMetodologia(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}
}