package Calculos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public  class Mediana extends Calculo {
	
	//@Transient
	private static Mediana instancia = null; 
	public static Mediana getSingletonMediana(){
		if(instancia == null){
			instancia = new Mediana();
		}
		return instancia;
	}
	
	
	public Mediana() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double realizarCalculo(List<Double> listaDeResultados){
		int cantidadDeElementos = listaDeResultados.size();
		double mediana = listaDeResultados.get(cantidadDeElementos/2);
		if(cantidadDeElementos % 2 !=0){
			mediana +=listaDeResultados.get(cantidadDeElementos/2+1);
			mediana /= 2;
		}
		return mediana;
	}
	@Override
	public String toString(){
		return "Mediana";
	}
}
