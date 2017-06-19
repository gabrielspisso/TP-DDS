package model;


public class claseDePruebas {

	public static void main(String[] args) {
		Indicador indicador = IndicadorBuilder.Build("a = 2*2/4 + 3*-1;");
		System.out.println(indicador.calcularValor(null, null));
	}
}
