import static org.junit.Assert.*;
import model.CargadorDeEmpresas;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.runners.*;
import org.junit.Test;

import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.condicionesYMetodologias.CondicionConAnio;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeCondiciones;
import model.repositorios.RepositorioDeCondicionesMock;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeEmpresasMock;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeIndicadoresInterfaz;
import model.repositorios.RepositorioDeIndicadoresMockeado;
import model.repositorios.RepositorioDeMetodologias;
import model.repositorios.RepositorioDeMetodologiasMockeado;
import model.repositorios.RepositorioDeUsuario;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testPersistencia {
	RepositorioDeEmpresasMock repositorioDeEmpresasMock = new RepositorioDeEmpresasMock();
	RepositorioDeIndicadoresInterfaz repoDeIndicadores = new RepositorioDeIndicadoresMockeado();
	RepositorioDeMetodologiasMockeado repoMetodologia = new RepositorioDeMetodologiasMockeado();
	RepositorioDeUsuario repoUsuario = new RepositorioDeUsuario();
	@Test
	public void test0_cargarEmpresas() {
		long p = (long) 1;
		Metodologia metodologiaAimplementar = repoMetodologia.buscarPorId(p);
		Empresa emp2 = repositorioDeEmpresasMock.buscarPorId("2");
		
		Usuario usuario = new Usuario("GabrielSpisso","123");
		repoUsuario.agregarUsuario(usuario);
		Usuario usuario2 = new Usuario("GabrielMaiori","123");
		repoUsuario.agregarUsuario(usuario2);
		
		Usuario usuario3 = new Usuario("SantiagoParedes","123");
		repoUsuario.agregarUsuario(usuario3);
		
		
		CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().forEach(emp->repositorioDeEmpresasMock.agregarEmpresas(emp));
	}	
	
	@Test
	public void test1_existeFacebook() {
		assertTrue(repositorioDeEmpresasMock.existe("Facebook"));
	}
	
	@Test
	public void test2_creoUnaEmpresaYPrueboQueExiste() {
		repositorioDeEmpresasMock.agregarEmpresas(new Empresa("EmpresaNueva", null));
		assertTrue(repositorioDeEmpresasMock.existe("EmpresaNueva"));
	}
	@Test
	public void test3_borroLaEmpresaDelTestAnteriorYComprueboQueYaNoExiste() {
		repositorioDeEmpresasMock.borrar("EmpresaNueva");
		assertFalse(repositorioDeEmpresasMock.existe("EmpresaNueva"));
	}
	@Test
	public void test4_existeIndicador1() {
		assertTrue(repoDeIndicadores.existe("indicador1"));
	}
	
	@Test
	public void test5_creoUnIndicadorYPrueboQueExiste() {
		repoDeIndicadores.agregarIndicador(IndicadorBuilder.Build("love = 123 * 4 +2;",repoDeIndicadores));
		assertTrue(repoDeIndicadores.existe("love"));
	}
	@Test
	public void test6_borroElIndicadorDelTestAnteriorYComprueboQueYaNoExiste() {
		repoDeIndicadores.borrar("love");
		assertFalse(repoDeIndicadores.existe("love"));
	}
	
	@Test
	public void test7_creoUnaMetodologiaYPrueboQueExiste() {
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;",repoDeIndicadores);
		
		
		CondicionConAnio test = new CondicionConAnio(indicador,Menor.getSingletonMenor(),8,1,"");
		CondicionEntreDosEmpresas test2 = new CondicionEntreDosEmpresas(repoDeIndicadores.buscar("indicador1"), Mayor.getSingletonMayor(), "hola");
		CondicionConAnio test3 = new CondicionConAnio(indicador,Mayor.getSingletonMayor(),8,1,"");
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test,  test3, test),Arrays.asList(test2), repoUsuario.buscarPorId("1"));
		
		RepositorioDeCondicionesMock Repo = new RepositorioDeCondicionesMock();
		
		Repo.agregarCondicion(test);
		RepositorioDeMetodologiasMockeado RepoMetodologia = new RepositorioDeMetodologiasMockeado();
		RepoMetodologia.agregarMetodologia(metodologia);
		
		
		
		assertTrue(RepoMetodologia.existe("Esto es una prueba"));
	}
	/*
	@Test
	public void test8_borroLaEmpresaDelTestAnteriorYComprueboQueYaNoExiste() {
		RepositorioDeMetodologias.borrar("Esto es una prueba");
		assertFalse(RepositorioDeMetodologias.existe("Esto es una prueba"));
	}*/
	
}
