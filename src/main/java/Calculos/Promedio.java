package Calculos;

import java.util.List;
import java.util.stream.Collectors;

public class Promedio extends Calculo {
	@Override
	public double realizarCalculo(List<Double> listaDeResultados){
		return new Sumatoria().realizarCalculo(listaDeResultados) / listaDeResultados.size();
	}
}
