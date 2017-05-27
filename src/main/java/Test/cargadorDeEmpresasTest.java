package Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.Balance;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;

public class cargadorDeEmpresasTest {

	@Test
	public void testNombresDeLaEmpresasCargadas() {
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		List<Cuenta> listaDeCuentas2 = Arrays.asList(new Cuenta("EBITDA",1));
		List<Cuenta> listaDeCuentas3 = Arrays.asList(new Cuenta("FREE CASH FLOW",3));
		List<Cuenta> listaDeCuentas4 = Arrays.asList(new Cuenta("EBITDA",1),new Cuenta("INGRESO NETO DISCONTINUO",25000),new Cuenta("FDS",5000));
		
		List<Balance> listaDeBalances = Arrays.asList(new Balance(2017,"Primer Semestre",listaDeCuentas),new Balance(2017,"Segundo Semestre",listaDeCuentas2));
		List<Balance> listaDeBalances2 = Arrays.asList(new Balance(2016,"Anual",listaDeCuentas3));
		List<Balance> listaDeBalances3 = Arrays.asList(new Balance(2016,"Segundo Semestre",listaDeCuentas4));
		
		List<Empresa> x = Arrays.asList(new Empresa ("Facebook",listaDeBalances),new Empresa("Twitter",listaDeBalances2),new Empresa("Instagram",listaDeBalances3));
		assertEquals("Facebook", CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt").get(0).toString());
		
		
		assertEquals(x.get(0).toString(), CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt").get(0).toString());
		assertEquals(x.get(1).toString(), CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt").get(1).toString());
		assertEquals(x.get(2).toString(), CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt").get(2).toString());
	
	}
	
	@Test
	public void testListaDeEmpresasCargadasCorrectamente() {
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		List<Cuenta> listaDeCuentas2 = Arrays.asList(new Cuenta("EBITDA",1));
		List<Cuenta> listaDeCuentas3 = Arrays.asList(new Cuenta("FREE CASH FLOW",3));
		List<Cuenta> listaDeCuentas4 = Arrays.asList(new Cuenta("EBITDA",1),new Cuenta("INGRESO NETO DISCONTINUO",25000),new Cuenta("FDS",5000));
		
		List<Balance> listaDeBalances = Arrays.asList(new Balance(2017,"Primer Semestre",listaDeCuentas),new Balance(2017,"Segundo Semestre",listaDeCuentas2));
		List<Balance> listaDeBalances2 = Arrays.asList(new Balance(2016,"Anual",listaDeCuentas3));
		List<Balance> listaDeBalances3 = Arrays.asList(new Balance(2016,"Segundo Semestre",listaDeCuentas4));
		
		List<Empresa> x = Arrays.asList(new Empresa ("Facebook",listaDeBalances),new Empresa("Twitter",listaDeBalances2),new Empresa("Instagram",listaDeBalances3));
		
		assertEquals(x, CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
	}
	
}
