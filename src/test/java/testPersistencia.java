import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.runners.*;
import org.junit.Test;

import model.Empresa;
import repositorios.Repositorio;
import repositorios.RepositorioDeEmpresas;
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
	
}
