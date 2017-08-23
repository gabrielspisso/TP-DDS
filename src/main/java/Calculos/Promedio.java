package Calculos;

import java.util.List;
import java.util.stream.Collectors;

public class Promedio implements Calculo {

	public double realizarCalculo(List<Double> listaDeResultados){
		return new Sumatoria().realizarCalculo(listaDeResultados) / listaDeResultados.size();
	}
	@Override
	public String toString(){
		return "Promedio";
	}
}
