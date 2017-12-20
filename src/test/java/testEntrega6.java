import static org.junit.Assert.*;

import org.junit.Test;

import model.Balance;
import model.CargadorDeEmpresas;
import model.IOs;
import model.Indicador;
import model.LeerEmpresasProgramado;
import model.repositorios.RepositorioDeArchivosMockeado;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeEmpresasMock;
import model.repositorios.RepositorioDeIndicadoresInterfaz;
import model.repositorios.RepositorioDeIndicadoresMockeado;

public class testEntrega6 {

	RepositorioDeIndicadoresInterfaz repo = new RepositorioDeIndicadoresMockeado();
	@Test
	public void testearQueSeGuardaElPrecalculo() {
		new LeerEmpresasProgramado("archivoEmpresas.txt").run(new RepositorioDeEmpresasMock(), repo, new RepositorioDeArchivosMockeado());;
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada(repo).get(0);
		//Balance balance = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0).getBalances().get(0);
		Balance balance =CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0).getBalances().get(0);
		indicador.evaluarBalance(balance,repo);
		assertTrue(balance.estaPrecalculadoElIndicador(indicador));
		//new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		
	}
	@Test
	public void testearQueSeGuardaElValorCorrecto() {
		new LeerEmpresasProgramado("archivoEmpresas.txt").run(new RepositorioDeEmpresasMock(), repo, new RepositorioDeArchivosMockeado());;
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada(repo).get(0);
		//Balance balance = RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0).getBalances().get(0);
		Balance balance =CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0).getBalances().get(0);
		String valor = indicador.evaluarBalance(balance,repo).getResultado();
		assertEquals(valor,balance.buscarEnListaDeResultados(indicador).toString());
		//new LeerEmpresasProgramado("archivoEmpresas.txt").run();;
		
		
	}

}
