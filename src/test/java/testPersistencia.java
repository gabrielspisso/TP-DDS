import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;

import model.Empresa;
import repositorios.Repositorio;
import repositorios.RepositorioDeEmpresas;

public class testPersistencia {

	@Test
	public void existeFacebook() {
		assertTrue(RepositorioDeEmpresas.existe("Facebook"));
	}
	
	@Test
	public void creoUnaEmpresaYPrueboQueExiste() {
		System.out.println("pito 111111");
		RepositorioDeEmpresas.agregarEmpresas(new Empresa("EmpresaNueva", null));
		assertTrue(RepositorioDeEmpresas.existe("EmpresaNueva"));
	}
	@Test
	public void borroLaEmpresaDelTestAnteriorYComprueboQueYaNoExiste() {
		RepositorioDeEmpresas.borrar("EmpresaNueva");
		System.out.println("pito 2222");
		assertFalse(RepositorioDeEmpresas.existe("EmpresaNueva"));
	}
	
}
