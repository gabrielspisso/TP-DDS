package Calculos;

public class Menor implements criterioDeAceptacionDeCondicion{
	@Override
	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo >= valorActual;
	}

}
