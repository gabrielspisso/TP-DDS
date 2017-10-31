package model.Calculos;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Menor extends criterioDeAceptacionDeCondicion{
	
	private Menor() {
		super();
	}

	//@Transient
	private static Menor instancia = null; 
	public static Menor getSingletonMenor(){
		if(instancia == null){
			instancia = new Menor();
		}
		return instancia;
	}
	
	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo >= valorActual;
	}

	@Override
	public String toString(){
		return "Menor";
	}
}
