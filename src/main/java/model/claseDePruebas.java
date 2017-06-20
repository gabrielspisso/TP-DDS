package model;

import java.util.Arrays;
import java.util.List;

import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;

public class claseDePruebas {

	public static void main(String[] args) {
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		Indicador indicador = IndicadorBuilder.Build("j = 1*2 + FREE CASH FLOW *4/4 + 6;");
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas("archivoEmpresas.txt"));
		
		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		
		
		System.out.println(indicador.calcularValor(listaDeCuentas, RepositorioDeIndicadores.getListaDeIndicadores()));
	}
}
