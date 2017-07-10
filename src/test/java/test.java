import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Calculos.Mayor;
import Calculos.Menor;
import Calculos.Promedio;
import Calculos.Sumatoria;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConComportamiento;
import condicionesYMetodologias.Metodologia;
import condicionesYMetodologias.CondicionConA�o;
import condicionesYMetodologias.condicionConCalculo;
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
	public void CreoUnaDivisionConUnaHojaDeValor0YOtraDeValor24YCalculoElResultadoQueEs0() {
		Hoja hoja1 = new Numero("0");
		Hoja hoja2 = new Numero("24");
		Operacion op = new DIVISION(hoja1, hoja2);
		assertEquals(0, op.calcularValor(null, null), DELTA);
	}
	
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor24YOtraDeValor0YElResultadoEsInfinity() {
		Hoja hoja1 = new Numero("24");
		Hoja hoja2 = new Numero("0");
		Operacion op = new DIVISION(hoja1, hoja2);
		assertTrue(Double.isInfinite(op.calcularValor(null, null)));
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
	public void pruebaFacebookTieneCCMayorA3(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConA�o test = new CondicionConA�o(3, 1,indicador,false,new Mayor());
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaFacebookNoTieneCCMayorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConA�o test = new CondicionConA�o(30, 1,indicador,false,new Mayor());
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	
	
	
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMayorA9(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(new Promedio(),9,indicador,false,new Mayor()); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA9(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(new Promedio(),9,indicador,false,new Menor()); //Deberian ser estaticos
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicionConCalculoFacebookTieneUnCCpromedioMenorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		condicionConCalculo test = new condicionConCalculo(new Promedio(),30,indicador,false,new Menor()); //Deberian ser estaticos
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsCreciente(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,false,new Mayor());
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	
	@Test
	public void pruebaDeCondicionConComportamientoFacebookConCCEsDecreciente(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConComportamiento test = new CondicionConComportamiento(indicador,false,new Menor());
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	
	@Test
	public void testFacebookTieneCincuentaPorcientoDeccConCondicionConA�oYCondicionConCalculo(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConA�o test = new CondicionConA�o(300,1,indicador,false,new Mayor());
		condicionConCalculo test2 = new condicionConCalculo(new Promedio(),9,indicador,false,new Mayor()); //Deberian ser estaticos
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test,test2));
		assertEquals(50,metodologia.evaluarMetodologia(RepositorioDeEmpresas.mostrarEmpresas().get(0)));
	}
	@Test(expected = RuntimeException.class)
	public void pruebaDeQueUnaMetodologiaSeRechazaSinoCumpleUnaCondicionTaxativa(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		Indicador indicador = IndicadorBuilder.Build("cc=FDSA;");
		CondicionConA�o test = new CondicionConA�o(300,1,indicador,false,new Mayor());
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test));
		
		metodologia.evaluarMetodologia(RepositorioDeEmpresas.mostrarEmpresas().get(0));	
	}
	@Test
	public void pruebaOrdenarMetodologias(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		CondicionConA�o test = new CondicionConA�o(7,1,indicador,false,new Mayor());
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test));
		List<Empresa> listaDeEmpresas = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(1),RepositorioDeEmpresas.mostrarEmpresas().get(2),RepositorioDeEmpresas.mostrarEmpresas().get(0));
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(RepositorioDeEmpresas.mostrarEmpresas() ));
	}
	@Test
	public void pruebaOrdenarMetodologiasSegunIndicador4MayorA1(){
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		Indicador indicador = IndicadorBuilder.Build("indicador4=FDS+4;");
		CondicionConA�o test = new CondicionConA�o(1,1,indicador,true,new Mayor());
		Metodologia metodologia = new Metodologia("Esto es una prueba",Arrays.asList(test));
		List<Empresa> listaDeEmpresas = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(0));
		List<Empresa> listaDeEmpresa2s = Arrays.asList(RepositorioDeEmpresas.mostrarEmpresas().get(0),RepositorioDeEmpresas.mostrarEmpresas().get(1));
		
		assertEquals(listaDeEmpresas,metodologia.listarEmpresas(listaDeEmpresa2s));
	}
	
}
