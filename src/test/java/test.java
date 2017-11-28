import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Usuario;
import model.Arbol.Hojas.Operando;
import model.Arbol.Hojas.Numero;
import model.Arbol.Operaciones.Division;
import model.Arbol.Operaciones.Multiplicacion;
import model.Arbol.Operaciones.Nodo;
import model.Arbol.Operaciones.Suma;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.Calculos.Promedio;
import model.Calculos.Sumatoria;
import model.condicionesYMetodologias.Condicion;
import model.condicionesYMetodologias.CondicionConAnio;
import model.condicionesYMetodologias.CondicionConComportamiento;
import model.condicionesYMetodologias.Metodologia;
import model.condicionesYMetodologias.condicionConCalculo;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeEmpresasMock;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeIndicadoresMockeado;

public class test {
	RepositorioDeEmpresasMock repositorioDeEmpresasMock = new RepositorioDeEmpresasMock();
	
	private static final double DELTA = 1e-15;
	private RepositorioDeIndicadoresMockeado repo  = new RepositorioDeIndicadoresMockeado();
	@Test
	public void CreoUnaHojaDeValor2YCalculoSuValor() {
		Operando hoja = new Numero("2");
		assertEquals(2.0, hoja.calcularValor(null, null,repo), DELTA);
	}

	@Test
	public void CreoUnaMultiplicacionConDosHojasDeValor2YCalculoElResultadoQueEs4(){
		Operando hoja1 = new Numero("2");
		Operando hoja2 = new Numero("2");
		Nodo op = new Multiplicacion(hoja1, hoja2);
		assertEquals(4.0, op.calcularValor(null, null,repo), DELTA);
	}
	@Test
	public void CreoUnaMultiplicacionConUnaHojaDeValor2YOtraDeValor4YCalculoElResultadoQueEs8(){
		Operando hoja1 = new Numero("2");
		Operando hoja2 = new Numero("4");
		Nodo op = new Multiplicacion(hoja1, hoja2);
		assertEquals(8.0, op.calcularValor(null, null,repo), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor2YOtraDeValor4YCalculoElResultadoQueEsUnMedio(){
		Operando hoja1 = new Numero("2");
		Operando hoja2 = new Numero("4");
		Nodo op = new Division(hoja1, hoja2);
		assertEquals(0.5, op.calcularValor(null, null,repo), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor4YOtraDeValor2YCalculoElResultadoQueEs2(){
		Operando hoja1 = new Numero("4");
		Operando hoja2 = new Numero("2");
		Nodo op = new Division(hoja1, hoja2);
		assertEquals(2, op.calcularValor(null, null,repo), DELTA);
	}
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor0YOtraDeValor24YCalculoElResultadoQueEs0() {
		Operando hoja1 = new Numero("0");
		Operando hoja2 = new Numero("24");
		Nodo op = new Division(hoja1, hoja2);
		assertEquals(0, op.calcularValor(null, null,repo), DELTA);
	}
	
	@Test
	public void CreoUnaDivisionConUnaHojaDeValor24YOtraDeValor0YElResultadoEsInfinity() {
		Operando hoja1 = new Numero("24");
		Operando hoja2 = new Numero("0");
		Nodo op = new Division(hoja1, hoja2);
		assertTrue(Double.isInfinite(op.calcularValor(null, null,repo)));
	}
	
	@Test
	public void CreoUnaSumaConUn7YUn9YCalculoSuSumaQueEs16(){
		Operando hoja1 = new Numero("7");
		Operando hoja2 = new Numero("9");
		Nodo op = new Suma(hoja1, hoja2);
		assertEquals(16.0, op.calcularValor(null, null,repo), DELTA);
	}
	
	@Test
	public void CreoUnaSumaCompuestaPorUnaMultiplicacionDe2Y8YTambienPorUnaDivisionEntre5Y2YCalculoSuValorQueEs18Coma5(){
		Operando hoja2 = new Numero("2");
		Operando hoja5 = new Numero("5");
		Operando hoja8 = new Numero("8");
		
		Nodo mul = new Multiplicacion(hoja2, hoja8);
		Nodo div = new Division(hoja5, hoja2);
		
		Nodo op = new Suma(mul, div);
		assertEquals(18.5, op.calcularValor(null, null,repo), DELTA);
	}
	
	@Test
	public void CreoUnIndicadorConUnaSumaQueTieneUn5YUnMenos23YCalculoSuValorQueEsMenos18(){
		
		Operando hoja1 = new Numero("5");
		Operando hoja2 = new Numero("-23");
		Nodo op = new Suma(hoja1, hoja2);
		Usuario usr = new Usuario("alguien","otro");
		Indicador indicador = new Indicador("ind1", op, "", usr);
		assertEquals(-18.0, indicador.calcularValor(null,repo), DELTA);
	}
	
	@Test
	public void CreoUnIndicadorComoElTestAnteriorPeroDesdeElBuilderYCalculoElResultadoQueSigueSiendoMenos18(){
		Indicador indicador = IndicadorBuilder.Build("ind1 = 5 -23;",repo);
		assertEquals(-18.0, indicador.calcularValor(null,repo), DELTA);
	}
	
	@Test
	public void CargoDatosDeUnaEmpresaYMuestroLaFormula(){
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW + 6 + b;",repo);
		List <Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt");
		le.forEach(e -> repositorioDeEmpresasMock.agregarEmpresas(e));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt",repo);
		
		assertTrue(indicador.mostrarFormula().equals("1*2+FREE CASH FLOW+6+b"));
	}
	
	@Test
	public void CargoDatosDeUnaEmpresaYMuestroLaFormulaCompleta(){
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW + 6 + b;",repo);
		List <Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt");
		le.forEach(e -> repositorioDeEmpresasMock.agregarEmpresas(e));
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt",repo);

		assertTrue(indicador.mostrarFormulaCompleta().equals("j = 1*2+FREE CASH FLOW+6+b"));
	}
	
	@Test
	public void CargoDatosDeUnaEmpresaYCalculoValor(){
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW + 6 + b;",repo);
		List <Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt");
		le.forEach(e -> repositorioDeEmpresasMock.agregarEmpresas(e));
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt",repo);
		
		assertEquals(14.0, indicador.calcularValor(listaDeCuentas,repo), DELTA);
	}
	@Test
	public void pruebaFacebookTieneCCMayorA3(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		CondicionConAnio test = new CondicionConAnio(indicador,Mayor.getSingletonMayor(),3,1,"");
		assertTrue(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	@Test
	public void pruebaFacebookNoTieneCCMayorA30(){
		Indicador indicador = IndicadorBuilder.Build("cc=FDS+10;",repo);
		CondicionConAnio test = new CondicionConAnio(indicador,Mayor.getSingletonMayor(),30, 1, "");
		assertFalse(test.cumpleCondicion(repositorioDeEmpresasMock.traerEmpresasDeLaDB().get(0),repo));
	}
	
	@Test
	public void pruebaDeIndicadorConMultiplicacion(){
		Indicador indicador = IndicadorBuilder.Build("cc=2*2;",repo);
		assertEquals(4.0, indicador.calcularValor(null,repo), DELTA);
	}
	
	@Test
	public void pruebaDeIndicadorConMultiplicacion2(){
		Indicador indicador = IndicadorBuilder.Build("ind11212 = 20/4*5;",repo);
		assertEquals(25.0, indicador.calcularValor(null,repo), DELTA);
	}
	
	
}
