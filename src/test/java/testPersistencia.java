import static org.junit.Assert.*;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.runners.*;

import Calculos.Mayor;
import condicionesYMetodologias.CondicionConAño;
import condicionesYMetodologias.Metodologia;

import org.junit.Test;

import model.Empresa;
import model.Indicador;
import model.Builders.IndicadorBuilder;
import repositorios.Repositorio;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;
import repositorios.RepositorioDeMetodologias;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testPersistencia {

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
		CondicionConAño test = new CondicionConAño(indicador,Mayor.getSingletonMayor(),7,1,"");
		
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test));
		RepositorioDeMetodologias.agregarMetodologia(metodologia);
		assertTrue(RepositorioDeMetodologias.existe("Esto es una prueba"));
	}
	@Test
	public void test8_borroLaEmpresaDelTestAnteriorYComprueboQueYaNoExiste() {
		RepositorioDeMetodologias.borrar("Esto es una prueba");
		assertFalse(RepositorioDeMetodologias.existe("Esto es una prueba"));
	}
	
}
