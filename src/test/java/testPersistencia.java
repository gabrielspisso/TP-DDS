import static org.junit.Assert.*;
import model.CargadorDeEmpresas;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.runners.*;

import mockers.CargadorDeEmpresasMock;

import org.junit.Test;

import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.condicionesYMetodologias.CondicionConAño;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeCondiciones;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeMetodologias;
import model.repositorios.RepositorioDeUsuario;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testPersistencia {

	@Test
	public void test0_cargarEmpresas() {
		long p = (long) 1;
		Metodologia metodologiaAimplementar = Repositorio.buscarPorId(p, Metodologia.class);
		Empresa emp2 = Repositorio.buscarPorId((long)2, Empresa.class);
		
		Usuario usuario = new Usuario("GabrielSpisso","123");
		RepositorioDeUsuario.agregarUsuario(usuario);
		Usuario usuario2 = new Usuario("GabrielMaiori","123");
		RepositorioDeUsuario.agregarUsuario(usuario2);
		
		Usuario usuario3 = new Usuario("SantiagoParedes","123");
		RepositorioDeUsuario.agregarUsuario(usuario3);
		
		
		CargadorDeEmpresasMock.obtenerCuentasEmpresas().forEach(emp-> RepositorioDeEmpresas.agregarEmpresas(emp));
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
		
		CondicionConAño test = new CondicionConAño(indicador,Menor.getSingletonMenor(),8,1,"");
		CondicionEntreDosEmpresas test2 = new CondicionEntreDosEmpresas(Repositorio.buscar("indicador1", Indicador.class), Mayor.getSingletonMayor(), "hola");
		CondicionConAño test3 = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test, test3), Arrays.asList(test2), Repositorio.buscarPorId((long)1, Usuario.class));
		
		RepositorioDeCondiciones.agregarCondicion(test);
		RepositorioDeMetodologias.agregarMetodologia(metodologia);
		
		assertTrue(RepositorioDeMetodologias.existe("Esto es una prueba"));
	}
}
