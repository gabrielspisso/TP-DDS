import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito.*;
import org.mockito.*;
import org.mockito.Mock;

import org.mockito.Matchers.*;
import model.Indicador;

public class testMocking {

	@Test
	public void test() {
		
		Indicador ind = Mockito.mock(Indicador.class);
		Mockito.when(ind.getNombre()).thenReturn("hola");
		//Assert.assertEquals(servicioB.multiplicar(2, 3),6);
		
		fail("Not yet implemented");
	}

}
