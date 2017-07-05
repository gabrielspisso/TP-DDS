package Calculos;

import java.util.List;

public class Mediana extends Calculo {
	
	@Override
	public double realizarCalculo(List<Double> listaDeResultados){
		int cantidadDeElementos = listaDeResultados.size();
		double mediana = listaDeResultados.get(cantidadDeElementos/2);
		if(cantidadDeElementos % 2 !=0){
			mediana +=listaDeResultados.get(cantidadDeElementos/2+1);
			mediana /= 2;
		}
		return mediana;
	}
}
