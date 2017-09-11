package Calculos;

import java.util.List;

import javax.persistence.Entity;

//@Entity
public  class Mediana extends Calculo {
	
	
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
