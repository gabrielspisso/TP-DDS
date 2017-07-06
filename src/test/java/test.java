import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Calculos.Promedio;
import Calculos.Sumatoria;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionTipo4;
import condicionesYMetodologias.Metodologia;
import condicionesYMetodologias.condicionTipo1;
import condicionesYMetodologias.condicionTipo3;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Termino;
import model.Arbol.Hojas.Hoja;
import model.Arbol.Hojas.Numero;
import model.Arbol.Operaciones.DIVISION;
import model.Arbol.Operaciones.MULTIPLICACION;
import model.Arbol.Operaciones.Operacion;
import model.Builders.IndicadorBuilder;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;

public class test {

	private static final double DELTA = 1e-15;
	@Test
	public void CreoUnaHojaDeValor2YCalculoSuValor() {
		Hoja hoja = new Numero("2");
		assertEquals(2.0, hoja.calcularValor(null, null), DELTA);
	}

	@Test
	public void CreoUnaMultiplicacionConDosHojasDeValor2YCalculoElResultadoQueEs4(){
		Hoja hoja1 = new Numero("2");
		Hoja hoja2 = new Numero("2");
		Operacion op = new MULTIPLICACION(hoja1, hoja2);
		assertEquals(4.0, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaMultiplicacionConUnaHojaDeValor2YOtraDeValor4YCalculoElResultadoQueEs8(){
		Hoja hoja1 = new Numero("2");
		Hoja hoja2 = new Numero("4");
		Operacion op = new MULTIPLICACION(hoja1, hoja2);
		assertEquals(8.0, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor2YOtraDeValor4YCalculoElResultadoQueEsUnMedio(){
		Hoja hoja1 = new Numero("2");
		Hoja hoja2 = new Numero("4");
		Operacion op = new DIVISION(hoja1, hoja2);
		assertEquals(0.5, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor4YOtraDeValor2YCalculoElResultadoQueEs2(){
		Hoja hoja1 = new Numero("4");
		Hoja hoja2 = new Numero("2");
		Operacion op = new DIVISION(hoja1, hoja2);
		assertEquals(2, op.calcularValor(null, null), DELTA);
	}
	
	@Test
	public void CreoDosTerminosUnoPositivoConUn7YOtroPositivoConUn9YCalculoSuSumaQueEs16(){
		Termino termino1 = new Termino("+", new Numero("7"));
		Termino termino2 = new Termino("+", new Numero("9"));
		assertEquals(16.0, termino1.calcularValor(null, null) + termino2.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoDosTerminosUnoPositivoConUn7YOtroNegativoConUn9YCalculoSuSumaQueEsMenos2(){
		Termino termino1 = new Termino("+", new Numero("7"));
		Termino termino2 = new Termino("-", new Numero("9"));
		assertEquals(-2.0, termino1.calcularValor(null, null) + termino2.calcularValor(null, null), DELTA);
	}
	
	@Test
	public void CreoDosTerminosComoLosDelTestAnteriorYLosPongoEnUnaListaParaCalcularSuValor(){
		Termino termino1 = new Termino("+", new Numero("7"));
		Termino termino2 = new Termino("-", new Numero("9"));
		List<Termino> lista = Arrays.asList(termino1,termino2);
		assertEquals(-2.0, 
				lista.stream().mapToDouble(termino -> termino.calcularValor(null, null)).sum(), DELTA);
	}
	
	@Test
	public void CreoUnIndicadorConDosTerminosQueTienenUn5YUnMenos23YCalculoSuValorQueEsMenos18(){
		
		Termino termino1 = new Termino("+", new Numero("5"));
		Termino termino2 = new Termino("-", new Numero("23"));
		Indicador indicador = new Indicador("ind1", Arrays.asList(termino1,termino2));
		assertEquals(-18.0, indicador.calcularValor(null), DELTA);
	}
	@Test
	public void CreoUnIndicadorComoElTestAnteriorPeroDesdeElBuilderYCalculoElResultadoQueSigueSiendoMenos18(){
		Indicador indicador = IndicadorBuilder.Build("ind1 = 5 -23;");
		assertEquals(-18.0, indicador.calcularValor(null), DELTA);
	}
	
	@Test
	public void CargoDatosDeUnaEmpresaYMuestroLaFormula(){
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW + 6 + b;");
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		
		assertTrue(indicador.mostrarFormula().equals("1*2+FREE CASH FLOW+6+b"));
	}
	
	@Test
	public void CargoDatosDeUnaEmpresaYMuestroLaFormulaCompleta(){
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW + 6 + b;");
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");

		assertTrue(indicador.mostrarFormulaCompleta().equals("j = 1*2+FREE CASH FLOW+6+b"));
	}
	
	@Test
	public void CargoDatosDeUnaEmpresaYCalculoValor(){
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW + 6 + b;");
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		
		assertEquals(14.0, indicador.calcularValor(listaDeCuentas), DELTA);
	}
	@Test
	public void algo(){
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador1 = IndicadorBuilder.Build("indicador1 = indicador2;");
		RepositorioDeIndicadores.agregarIndicador(indicador1);
		Indicador indicador2 = IndicadorBuilder.Build("indicador2 = 2;");
		RepositorioDeIndicadores.agregarIndicador(indicador2);
		assertEquals(2.0,indicador2.calcularValor(listaDeCuentas),DELTA);
	}
	@Test
	public void pruebaDeCondicion1(){
		condicionTipo1 test = new condicionTipo1(3, 1,RepositorioDeIndicadores.getListaDeIndicadores().get(2),false);
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	
	@Test
	public void pruebaDeCondicion3(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		condicionTipo3 test = new condicionTipo3(new Promedio(),9,RepositorioDeIndicadores.getListaDeIndicadores().get(2),false); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicion4(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		CondicionTipo4 test = new CondicionTipo4("Creciente",RepositorioDeIndicadores.getListaDeIndicadores().get(2),false); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaMetodologias(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		condicionTipo1 test = new condicionTipo1(300,1,RepositorioDeIndicadores.getListaDeIndicadores().get(1),false);
		condicionTipo3 test2 = new condicionTipo3(new Promedio(),9,RepositorioDeIndicadores.getListaDeIndicadores().get(1),false); //Deberian ser estaticos
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test,test2));
		assertEquals(50,metodologia.evaluarMetodologia(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaOrdenarMetodologias(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		condicionTipo1 test = new condicionTipo1(7,1,RepositorioDeIndicadores.getListaDeIndicadores().get(0),false);
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test));
		List<Empresa> listaDeEmpresas = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(1),RepositorioDeEmpresas.mostrarEmpresas().get(2),RepositorioDeEmpresas.mostrarEmpresas().get(0));
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(RepositorioDeEmpresas.mostrarEmpresas() ));
	}
	
}
