package model;

import view.menuPrincipal;

public class claseDePruebas {

	public static void main(String[] args) {
		Indicador indicador = IndicadorBuilder.Build("a = 2*2 + 3 +2/2;");
		System.out.println(indicador.calcularValor(null, null));
	}
}
