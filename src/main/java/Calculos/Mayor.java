package Calculos;

public class Mayor implements criterioDeAceptacionDeCondicion{

	@Override
	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo <= valorActual;
	}

}
