


import static org.junit.Assert.*;

import org.junit.Test;

import model.IOs;

public class ArchivoMockingTest {

	@Test
	public void testLecturaDeArchivo() {
	String aux = "[{\"nombre\":\"Facebook\",\"balances\":[{\"periodo\":\"Primer Semestre\",\"anio\":\"2017\",\"cuentas\":[{\"nombre\":\"FDS\",\"valor\":1},{\"nombre\":\"FREE CASH FLOW\",\"valor\":2}]},{\"periodo\":\"Segundo Semestre\",\"anio\":\"2017\",\"cuentas\":[{\"nombre\":\"EBITDA\",\"valor\":1}]}]},{\"nombre\":\"Twitter\",\"balances\":[{\"periodo\":\"Anual\",\"anio\":\"2016\",\"cuentas\":[{\"nombre\":\"FREE CASH FLOW\",\"valor\":3}]}]},{\"nombre\":\"Instagram\",\"balances\":[{\"periodo\":\"Segundo Semestre\",\"anio\":\"2016\",\"cuentas\":[{\"nombre\":\"EBITDA\",\"valor\":1},{\"nombre\":\"INGRESO NETO DISCONTINUO\",\"valor\":25000},{\"nombre\":\"FDS\",\"valor\":5000}]}]}]";
	assertEquals(aux,
				IOs.readFileAsString("archivoEmpresas.txt"));
	}

}