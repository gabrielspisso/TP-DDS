import static org.junit.Assert.*;
import model.CargadorDeEmpresas;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.runners.*;
import org.junit.Test;

import model.Empresa;
import model.Indicador;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.condicionesYMetodologias.CondicionConAņo;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeCondiciones;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeMetodologias;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testPersistencia {

	@Test
	public void test0_cargarEmpresas() {
		long p = (long) 1;
		Metodologia metodologiaAimplementar = Repositorio.buscarPorId(p, Metodologia.class);
		Empresa emp2 = Repositorio.buscarPorId((long)2, Empresa.class);
		CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().forEach(emp-> RepositorioDeEmpresas.agregarEmpresas(emp));
	}
	
	@Test
	public void test1_existeFacebook() {
		assertTrue(RepositorioDeEmpresas.existe("Facebook"));
	}
	
	@Test
	public void test2_creoUnaEmpresaYPrueboQueExiste() {
		RepositorioDeEmpresas.agregarEmpresas(new Empresa("EmpresaNueva", null));
		assertTrue(RepositorioDeEmpresas.existe("EmpresaNueva"));
	}
	@Test
	public void test3_borroLaEmpresaDelTestAnteriorYComprueboQueYaNoExiste() {
		RepositorioDeEmpresas.borrar("EmpresaNueva");
		assertFalse(RepositorioDeEmpresas.existe("EmpresaNueva"));
	}
	@Test
	public void test4_existeIndicador1() {
		assertTrue(RepositorioDeIndicadores.existe("indicador1"));
	}
	
	@Test
	public void test5_creoUnIndicadorYPrueboQueExiste() {
		RepositorioDeIndicadores.agregarIndicador(IndicadorBuilder.Build("love = 123 * 4 +2;"));
		assertTrue(RepositorioDeIndicadores.existe("love"));
	}
	@Test
	public void test6_borroElIndicadorDelTestAnteriorYComprueboQueYaNoExiste() {
		RepositorioDeIndicadores.borrar("love");
		assertFalse(RepositorioDeIndicadores.existe("love"));
	}
	
	@Test
	public void test7_creoUnaMetodologiaYPrueboQueExiste() {
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		CondicionConAņo test = new CondicionConAņo(indicador,Menor.getSingletonMenor(),8,1,"");
		CondicionEntreDosEmpresas test2 = new CondicionEntreDosEmpresas(Repositorio.buscar("indicador1", Indicador.class), Mayor.getSingletonMayor(), "hola");
		CondicionConAņo test3 = new CondicionConAņo(indicador,Mayor.getSingletonMayor(),8,1,"");
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test, test2, test3, test));
		
		RepositorioDeCondiciones.agregarCondicion(test);
		
		RepositorioDeMetodologias.agregarMetodologia(metodologia);
		
		
		
		assertTrue(RepositorioDeMetodologias.existe("Esto es una prueba"));
	}
	/*
	@Test
	public void test8_borroLaEmpresaDelTestAnteriorYComprueboQueYaNoExiste() {
		RepositorioDeMetodologias.borrar("Esto es una prueba");
		assertFalse(RepositorioDeMetodologias.existe("Esto es una prueba"));
	}*/
	
}
