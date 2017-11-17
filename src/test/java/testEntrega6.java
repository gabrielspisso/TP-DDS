import static org.junit.Assert.*;

import org.junit.Test;

import model.Balance;
import model.CargadorDeEmpresas;
import model.IOs;
import model.Indicador;
import model.LeerEmpresasProgramado;
import model.repositorios.RepositorioDeEmpresas;

public class testEntrega6 {

	@Test
	public void testearQueSeGuardaElPrecalculo() {
		new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada().get(0);
		//Balance balance = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0).getBalances().get(0);
		Balance balance =CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0).getBalances().get(0);
		indicador.evaluarBalance(balance);
		assertTrue(balance.estaPrecalculadoElIndicador(indicador));
		//new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		
	}
	@Test
	public void testearQueSeGuardaElValorCorrecto() {
		new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada().get(0);
		//Balance balance = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0).getBalances().get(0);
		Balance balance =CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0).getBalances().get(0);
		String valor = indicador.evaluarBalance(balance).getResultado();
		assertEquals(valor,balance.buscarEnListaDeResultados(indicador).toString());
		//new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		
	}

}
