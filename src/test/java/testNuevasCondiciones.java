import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.Calculos.Promedio;
import model.condicionesYMetodologias.CondicionConAño;
import model.condicionesYMetodologias.CondicionConComportamiento;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.condicionesYMetodologias.condicionConCalculo;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeUsuario;

public class testNuevasCondiciones {
	Usuario user;
	@Before
	public void cargarEmpresas(){
		List<Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada();
		le.forEach(e -> RepositorioDeEmpresas.agregarEmpresas(e));
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		user = new Usuario("pipo", "");
	}	
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMayorA9(){

		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		condicionConCalculo test = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}
	
	@Test
	public void condicionConanioalgotieenequellegarasalir() {
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionConAño test = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}
	
	
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");

		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		
		assertTrue(test.cumpleLaCondicion(RepositorioDeEmpresas.traerEmpresasDeLaDB().get(0)));
	}
}