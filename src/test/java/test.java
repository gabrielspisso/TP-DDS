import static org.junit.Assert.*;

import org.junit.Test;

import Operaciones.Operacion;
import model.IndicadorOCuenta;
import model.Nodo;
import model.Numero;
import model.Raiz;

public class test {

	private static final double DELTA = 1e-15;
	@Test
	public void CreoUnaHojaDeValor2YCalculoSuValor() {
		Nodo hoja = new Numero("2");
		assertEquals(2.0, hoja.calcularValor(null, null), DELTA);
	}

	@Test
	public void CreoUnaOperacionConUnaHojaDeValor2YCalculoElResultadoQueEs2(){
		Operacion op = new Operacion(null, null);
		Nodo ramaIzquierda = new Numero("2");
		assertEquals(2.0, op.calcular("+", ramaIzquierda, null), DELTA);
	}
	@Test
	public void CreoUnaOperacionConDosHojasDeValor2YCalculoLaSumaQueEs4(){
		Operacion op = new Operacion(null, null);
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("2");
		assertEquals(4.0, op.calcular("+", ramaIzquierda, ramaDerecha), DELTA);
	}
	@Test
	public void CreoUnaOperacionConDosHojasDeValor2YCalculoLaMultiplicacionQueEs4(){
		Operacion op = new Operacion(null, null);
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("2");
		assertEquals(4.0, op.calcular("*", ramaIzquierda, ramaDerecha), DELTA);
	}
	@Test
	public void CreoUnaOperacionConDosHojasDeValor2YCalculoLaDivisionQueEs1(){
		Operacion op = new Operacion(null, null);
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("2");
		assertEquals(1.0, op.calcular("/", ramaIzquierda, ramaDerecha), DELTA);
	}
	@Test
	public void CreoUnaOperacionConDosHojasDeValor2YCalculoLaResaQueEs0(){
		Operacion op = new Operacion(null, null);
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("2");
		assertEquals(0, op.calcular("-", ramaIzquierda, ramaDerecha), DELTA);
	}
	
	//A partir de este punto los test solo tienen sumas en las pruebas
	
	@Test
	public void CreoUnaRaizConUnaHojaDeValor2YCalculoElResultadoQueEs2(){
		Nodo ramaIzquierda = new Numero("2");
		//Nodo ramaDerecha = new Numero("2");
		Nodo raiz = new Raiz("+", null, ramaIzquierda);
		assertEquals(2.0, raiz.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaRaizConDosHojasDeValor2YDeValor3YCalculoElResultadoQueEs5(){
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("3");
		Nodo raiz = new Raiz("+", ramaDerecha, ramaIzquierda);
		assertEquals(5.0, raiz.calcularValor(null, null), DELTA);
	}
	@Test
	public void CreoUnaRaizConUnaHojaYOtraRaizDentroYCalculoElResultadoQueEs8(){
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("3");
		Nodo raiz1 = new Raiz("+", ramaDerecha, ramaIzquierda);
		Nodo raiz2 = new Raiz("+", ramaDerecha, raiz1);
		assertEquals(8.0, raiz2.calcularValor(null, null), DELTA);
	}//Este test no quedo muy claro, pero la idea era probar raices anidadas
	
	@Test
	public void CreoUnaRaizConDosRaicesQueValen5CadaUnaElResultadoEs10(){
		Nodo ramaIzquierda = new Numero("2");
		Nodo ramaDerecha = new Numero("3");
		Nodo raiz1 = new Raiz("+", ramaDerecha, ramaIzquierda);
		Nodo raiz2 = new Raiz("+", ramaDerecha, ramaIzquierda);
		
		Nodo raiz3 = new Raiz("+", raiz1, raiz2);
		assertEquals(10.0, raiz3.calcularValor(null, null), DELTA);
	}
	
}
