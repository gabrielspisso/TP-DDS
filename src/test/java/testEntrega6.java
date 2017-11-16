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
	public void test() {
		new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada().get(0);
		System.out.println("Entre al coso ");
		Balance balance = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0).getBalances().get(0);
		//Balance balance =CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0).getBalances().get(0);
		indicador.evaluarBalance(balance);
		assertTrue(balance.estaPrecalculadoElIndicador(indicador));
		//new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		
	}
	@Test
	public void test2() {
		new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada().get(0);
		//new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		
	}

}
