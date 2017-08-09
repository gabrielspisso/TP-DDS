import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Calculos.Mayor;
import Calculos.Menor;
import Calculos.Promedio;
import condicionesYMetodologias.CondicionConAño;
import condicionesYMetodologias.CondicionConComportamiento;
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


	
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMayorA9(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
	
		//Parche provisiorio hasta saber como hacer que se ejecuten antes de todo los test.
		
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(new Promedio(),9,indicador,new Mayor()); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA9(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(new Promedio(),9,indicador,new Menor()); //Deberian ser estaticos
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(new Promedio(),30,indicador,new Menor()); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,new Mayor());
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsDecreciente(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,new Menor());
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void testFacebookTieneCincuentaPorcientoDeccConCondicionConAñoYCondicionConCalculo(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConAño test = new CondicionConAño(300,1,indicador,new Mayor());
		condicionConCalculo test2 = new condicionConCalculo(new Promedio(),9,indicador,new Mayor()); //Deberian ser estaticos
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test,test2));
		assertEquals(50,metodologia.evaluarMetodologia(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	
	@Test
	public void pruebaOrdenarMetodologias(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		CondicionConAño test = new CondicionConAño(7,1,indicador,new Mayor());
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test));
		List<Empresa> listaDeEmpresas = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(1),RepositorioDeEmpresas.mostrarEmpresas().get(2),RepositorioDeEmpresas.mostrarEmpresas().get(0));
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(RepositorioDeEmpresas.mostrarEmpresas() ));
	}
	@Test
	public void pruebaOrdenarMetodologiasSegunIndicador4MayorA1(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		Indicador indicador = IndicadorBuilder.Build("indicador4=FDS+4;");
		CondicionConAño test = new CondicionConAño(1,1,indicador,new Mayor());
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test));
		List<Empresa> listaDeEmpresas = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(0));
		List<Empresa> listaDeEmpresa2s = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(0),RepositorioDeEmpresas.mostrarEmpresas().get(1));
		
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(listaDeEmpresa2s));
	}

}
