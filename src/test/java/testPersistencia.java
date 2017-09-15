import static org.junit.Assert.*;

import org.junit.Test;

import repositorios.Repositorio;
import repositorios.RepositorioDeEmpresas;

public class testPersistencia {

	@Test
	public void existeFacebook() {
		assertTrue(RepositorioDeEmpresas.existe("Facebook"));
	}
	
}
