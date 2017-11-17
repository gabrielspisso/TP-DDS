import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito.*;
import org.mockito.*;
import org.mockito.Mock;

import org.mockito.Matchers.*;
import model.Empresa;
import model.CargadorDeEmpresas;
import model.Indicador;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;

public class testMocking {

	@Test
	public void test() {
		
		Indicador ind = Mockito.mock(Indicador.class);
		Mockito.when(ind.getNombre()).thenReturn("hola");
		List<Indicador> as;
		RepositorioDeEmpresas repo = Mockito.mock(RepositorioDeEmpresas.class);
		
		
		Mockito.when(repo.traerEmpresasDeLaDB()).thenReturn(CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada());
		
		//Assert.assertEquals(servicioB.multiplicar(2, 3),6);
		
		fail("Not yet implemented");
	}

}
