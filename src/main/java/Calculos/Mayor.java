package Calculos;

public class Mayor implements criterioDeAceptacionDeCondicion{

	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo <= valorActual;
	}

}
