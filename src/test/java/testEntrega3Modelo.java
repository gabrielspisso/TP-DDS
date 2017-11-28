import static org.junit.Assert.*;

import java.util.ArrayList;
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
import model.condicionesYMetodologias.CondicionConAnio;
import model.condicionesYMetodologias.CondicionConComportamiento;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.condicionesYMetodologias.condicionConCalculo;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeEmpresasMock;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeIndicadoresInterfaz;
import model.repositorios.RepositorioDeIndicadoresMockeado;
import model.repositorios.RepositorioDeUsuario;

public class testEntrega3Modelo {
	RepositorioDeEmpresasMock repositorioDeEmpresasMock = new RepositorioDeEmpresasMock();
	RepositorioDeIndicadoresInterfaz repo = new RepositorioDeIndicadoresMockeado();
	Usuario user;
	@Before
	public void cargarEmpresas(){
		List<Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada();
		le.forEach(e -> repositorioDeEmpresasMock.agregarEmpresas(e));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt",repo);
		user = new Usuario("pipo", "");
	}	
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMayorA9(){
	
		//Parche provisiorio hasta saber como hacer que se ejecuten antes de todo los test.
		
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		condicionConCalculo test = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,""); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA9(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		condicionConCalculo test = new condicionConCalculo(indicador,Menor.getSingletonMenor(),Promedio.getSingletonPromedio(),9,""); //Deberian ser estaticos
		assertFalse(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		condicionConCalculo test = new condicionConCalculo(indicador,Menor.getSingletonMenor(),Promedio.getSingletonPromedio(),30,""); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente(){
		//RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		assertTrue(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente2(){
		//RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=sadsagaga+10;",repo);
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		assertFalse(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsDecreciente(){
		//RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,Menor.getSingletonMenor(),2,"");
		assertFalse(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	@Test
	public void testFacebookTieneCincuentaPorcientoDeccConCondicionConAnioYCondicionConCalculo(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		CondicionConAnio test = new CondicionConAnio(indicador,Mayor.getSingletonMayor(),300,1,"");
		condicionConCalculo test2 = new condicionConCalculo(indicador,Mayor.getSingletonMayor(),Promedio.getSingletonPromedio(),9,""); //Deberian ser estaticos
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test,test2),null,user);
		assertEquals(50,metodologia.sacarPorcentajeMetodologia(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	

	@Test
	public void pruebaCondicionDosEmpresas(){
		Indicador indicador =IOs.listaDeIndicadoresMockeada(repo).get(0);
		Empresa facebook = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0);
		Empresa twitter = CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(1);
		CondicionEntreDosEmpresas test = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"");
		
		assertEquals(test.compararEmpresas(facebook, twitter,repo),-1);
	}
	@Test
	public void pruebaOrdenarMetodologias(){
		Indicador indicador =IOs.listaDeIndicadoresMockeada(repo).get(0);
		
		CondicionConAnio test = new CondicionConAnio(indicador,Mayor.getSingletonMayor(),7,1,"");
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test),new ArrayList<CondicionEntreDosEmpresas>(),user);
		List<Empresa> listaDeEmpresas = Arrays.asList(CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(1),CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(2),CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().get(0));
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada(),repo ));
	}
	
	@Test
	public void testDemierda(){
		Indicador indicador =IOs.listaDeIndicadoresMockeada(repo).get(0);
		CondicionConAnio test = new CondicionConAnio(indicador,Mayor.getSingletonMayor(),8,1,"");
		CondicionConAnio test2 = new CondicionConAnio(indicador,Menor.getSingletonMenor(),8,1,"");
		Metodologia metodologiaAimplementar = new Metodologia("Metodologia 1",null,Arrays.asList(test, test2),new ArrayList<CondicionEntreDosEmpresas>(),user);
		
		Empresa emp = repositorioDeEmpresasMock.buscar("Twitter");
		System.out.println(metodologiaAimplementar.generarResultado(Arrays.asList(emp),repo).get(0).getValor());
		
		assertEquals(50, metodologiaAimplementar.sacarPorcentajeMetodologia(emp,repo));
	}
	
}
