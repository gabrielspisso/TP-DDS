import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Packete.Lista;

public class testDeClaseLista {

	private static final double DELTA = 1e-15;
	
	private Lista<Integer> listaEnteros(){
		return new Lista<Integer>(Arrays.asList(1,2,3,4,5,6,7));
	}
	private Lista<Double> listaDoubles(){
		return new Lista<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0));
	}
	@Test
	public void head() {
		Lista<Integer> lista = listaEnteros();
		assertEquals(1, (int)lista.head());
	}
	@Test
	public void find() {
		Lista<Integer> lista = listaEnteros();
		assertEquals(4, (int)lista.find(x->x == 4));
	}
	@Test
	public void last() {
		Lista<Integer> lista = listaEnteros();
		assertEquals(7, (int)lista.last());
	}
	@Test
	public void tailYAreEquals() {
		Lista<Integer> lista = listaEnteros();
		Lista<Integer> listaComoDeberiaQuedar = new Lista<Integer>(Arrays.asList(2,3,4,5,6,7));
		assertTrue(lista.tail().areEquals(listaComoDeberiaQuedar));
	}
	@Test
	public void sumInt() {
		Lista<Integer> lista = listaEnteros();
		assertEquals(28, lista.sumInt(x-> x.intValue()));
	}
	@Test
	public void sumDouble() {
		Lista<Double> lista = listaDoubles();
		assertEquals(28.0, lista.sumDouble(x-> x.intValue()),DELTA);
	}
	
	@Test
	public void drop() {
		Lista<Integer> lista = listaEnteros();
		Lista<Integer> listaComoDeberiaQuedar = new Lista<Integer>(Arrays.asList(4,5,6,7));
		assertTrue(lista.drop(3).areEquals(listaComoDeberiaQuedar));
	}
	@Test
	public void take() {
		Lista<Integer> lista = listaEnteros();
		Lista<Integer> listaComoDeberiaQuedar = new Lista<Integer>(Arrays.asList(1,2));
		assertTrue(lista.take(2).areEquals(listaComoDeberiaQuedar));
	}
	@Test
	public void popLast() {
		Lista<Integer> lista = listaEnteros();
		assertEquals(7, (int)lista.popLast());
		lista.popLast();
		lista.popLast();
		Lista<Integer> listaComoDeberiaQuedar = new Lista<Integer>(Arrays.asList(1,2,3,4));//Hice 3 pops
		assertTrue(lista.areEquals(listaComoDeberiaQuedar));
	}
	
	@Test
	public void filter() {
		Lista<Integer> lista = listaEnteros().filter(x->x>3);
		Lista<Integer> listaComoDeberiaQuedar = new Lista<Integer>(Arrays.asList(4,5,6,7));
		assertTrue(lista.areEquals(listaComoDeberiaQuedar));
	}

	@Test
	public void anyVerdadero() {
		Lista<Integer> lista = listaEnteros();
		assertTrue(lista.any(x->x.intValue()>5));
	}
	@Test
	public void anyFalso() {
		Lista<Integer> lista = listaEnteros();
		assertFalse(lista.any(x->x.intValue()<-1));
	}
	@Test
	public void allVerdadero() {
		Lista<Integer> lista = listaEnteros();
		assertTrue(lista.all(x->x.intValue()>0));
	}
	@Test
	public void allFalso() {
		Lista<Integer> lista = listaEnteros();
		assertFalse(lista.all(x->x.intValue()>50));
	}
}
