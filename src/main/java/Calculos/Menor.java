package Calculos;

import javax.persistence.Entity;

@Entity
public class Menor extends criterioDeAceptacionDeCondicion{
	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo >= valorActual;
	}
	@Override
	public String toString(){
		return "Menor";
	}
}
