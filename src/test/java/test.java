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
import condicionesYMetodologias.CondicionConAño;
import condicionesYMetodologias.condicionConCalculo;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Arbol.Hojas.Hoja;
import model.Arbol.Hojas.Numero;
import model.Arbol.Operaciones.Division;
import model.Arbol.Operaciones.Multiplicacion;
import model.Arbol.Operaciones.NODO;
import model.Arbol.Operaciones.Suma;
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
		NODO op = new Multiplicacion(hoja1, hoja2);
		assertEquals(4.0, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaMultiplicacionConUnaHojaDeValor2YOtraDeValor4YCalculoElResultadoQueEs8(){
		Hoja hoja1 = new Numero("2");
		Hoja hoja2 = new Numero("4");
		NODO op = new Multiplicacion(hoja1, hoja2);
		assertEquals(8.0, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor2YOtraDeValor4YCalculoElResultadoQueEsUnMedio(){
		Hoja hoja1 = new Numero("2");
		Hoja hoja2 = new Numero("4");
		NODO op = new Division(hoja1, hoja2);
		assertEquals(0.5, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor4YOtraDeValor2YCalculoElResultadoQueEs2(){
		Hoja hoja1 = new Numero("4");
		Hoja hoja2 = new Numero("2");
		NODO op = new Division(hoja1, hoja2);
		assertEquals(2, op.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor0YOtraDeValor24YCalculoElResultadoQueEs0() {
		Hoja hoja1 = new Numero("0");
		Hoja hoja2 = new Numero("24");
		NODO op = new Division(hoja1, hoja2);
		assertEquals(0, op.calcularValor(null, null), DELTA);
	}
	
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor24YOtraDeValor0YElResultadoEsInfinity() {
		Hoja hoja1 = new Numero("24");
		Hoja hoja2 = new Numero("0");
		NODO op = new Division(hoja1, hoja2);
		assertTrue(Double.isInfinite(op.calcularValor(null, null)));
	}
	
	@Test
	public void CreoUnaSumaConUn7YUn9YCalculoSuSumaQueEs16(){
		Hoja hoja1 = new Numero("7");
		Hoja hoja2 = new Numero("9");
		NODO op = new Suma(hoja1, hoja2);
		assertEquals(16.0, op.calcularValor(null, null), DELTA);
	}
	
	@Test
	public void CreoUnaSumaCompuestaPorUnaMultiplicacionDe2Y8YTambienPorUnaDivisionEntre5Y2YCalculoSuValorQueEs18Coma5(){
		Hoja hoja2 = new Numero("2");
		Hoja hoja5 = new Numero("5");
		Hoja hoja8 = new Numero("8");
		
		NODO mul = new Multiplicacion(hoja2, hoja8);
		NODO div = new Division(hoja5, hoja2);
		
		NODO op = new Suma(mul, div);
		assertEquals(18.5, op.calcularValor(null, null), DELTA);
	}
	
	@Test
	public void CreoUnIndicadorConUnaSumaQueTieneUn5YUnMenos23YCalculoSuValorQueEsMenos18(){
		
		Hoja hoja1 = new Numero("5");
		Hoja hoja2 = new Numero("-23");
		NODO op = new Suma(hoja1, hoja2);
		Indicador indicador = new Indicador("ind1", op, "");
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
		CondicionConAño test = new CondicionConAño(indicador,new Mayor(),3,1);
		assertTrue(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0), null));
	}
	@Test
	public void pruebaFacebookNoTieneCCMayorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;");
		CondicionConAño test = new CondicionConAño(indicador,new Mayor(),30, 1);
		assertFalse(test.cumpleCondicion(RepositorioDeEmpresas.mostrarEmpresas().get(0), null));
	}
	
	
	
}
