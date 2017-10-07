package model.Calculos;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Mayor extends criterioDeAceptacionDeCondicion{


	private Mayor() {
		super();
		// TODO Auto-generated constructor stub
	}
	///@Transient
	private static Mayor instancia = null; 
	public static Mayor getSingletonMayor(){
		if(instancia == null){
			instancia = new Mayor();
		}
		return instancia;
	
	}
	public boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual) {
		return valorMinimo <= valorActual;
	}
	@Override
	public String toString(){
		return "Mayor";
	}
	
}
