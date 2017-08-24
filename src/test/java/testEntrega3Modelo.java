import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Calculos.Mayor;
import Calculos.Menor;
import Calculos.Promedio;
import condicionesYMetodologias.CondicionConAño;
import condicionesYMetodologias.CondicionConComportamiento;
import condicionesYMetodologias.CondicionEntreDosEmpresas;
import condicionesYMetodologias.Metodologia;
import condicionesYMetodologias.condicionConCalculo;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Builders.IndicadorBuilder;
import repositorios.RepositorioDeEmpresas;

public class testEntrega3Modelo {

	
	@Before
	public void cargarEmpresas(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
	}	
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMayorA9(){
	
		//Parche provisiorio hasta saber como hacer que se ejecuten antes de todo los test.
		
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(indicador,new Mayor(),new Promedio(),9); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0),null));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA9(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(indicador,new Menor(),new Promedio(),9); //Deberian ser estaticos
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0),null));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(indicador,new Menor(),new Promedio(),30); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0),null));
	}
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,new Mayor(),2);
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0),null));
	}
	
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsDecreciente(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,new Menor(),2);
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0),null));
	}
	@Test
	public void testFacebookTieneCincuentaPorcientoDeccConCondicionConAñoYCondicionConCalculo(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConAño test = new CondicionConAño(indicador,new Mayor(),300,1);
		condicionConCalculo test2 = new condicionConCalculo(indicador,new Mayor(),new Promedio(),9); //Deberian ser estaticos
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test,test2));
		assertEquals(50,metodologia.sacarPorcentajeMetodologia(RepositorioDeEmpresas.mostrarEmpresas().get(0),null));
	}
	

	@Test
	public void pruebaCondicionDosEmpresas(){
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		
		CondicionEntreDosEmpresas test = new CondicionEntreDosEmpresas(indicador,new Mayor());
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0), RepositorioDeEmpresas.mostrarEmpresas().get(1)));
	}
	@Test
	public void pruebaOrdenarMetodologias(){
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		
		CondicionConAño test = new CondicionConAño(indicador,new Mayor(),7,1);
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test));
		List<Empresa> listaDeEmpresas = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(1),RepositorioDeEmpresas.mostrarEmpresas().get(2),RepositorioDeEmpresas.mostrarEmpresas().get(0));
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(RepositorioDeEmpresas.mostrarEmpresas() ));
	}
	
	
}
