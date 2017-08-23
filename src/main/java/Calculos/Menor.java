package Calculos;

public class Menor implements criterioDeAceptacionDeCondicion{
	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo >= valorActual;
	}
	@Override
	public String toString(){
		return "Menor";
	}
}
