package Calculos;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Sumatoria extends Calculo{
	
	
	public Sumatoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	private double total;
	public double realizarCalculo(List<Double> listaDeResultados){
		total = 0;
		listaDeResultados.forEach(x-> total+=x);
		return total;
	}
	@Override
	public String toString(){
		return "Sumatoria";
	}
}
