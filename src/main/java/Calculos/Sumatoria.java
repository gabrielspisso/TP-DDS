package Calculos;

import java.util.List;

public class Sumatoria implements Calculo{
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
