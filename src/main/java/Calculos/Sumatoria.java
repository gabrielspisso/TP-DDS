package Calculos;

import java.util.List;

public class Sumatoria extends Calculo{
	private double total;
	@Override
	public double realizarCalculo(List<Double> listaDeResultados){
		total = 0;
		listaDeResultados.forEach(x-> total+=x);	
		return total;
	}
}
