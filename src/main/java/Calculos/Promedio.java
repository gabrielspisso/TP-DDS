package Calculos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;

@Entity
public class Promedio extends Calculo {
	
	public Promedio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double realizarCalculo(List<Double> listaDeResultados){
		return new Sumatoria().realizarCalculo(listaDeResultados) / listaDeResultados.size();
	}
	@Override
	public String toString(){
		return "Promedio";
	}
}
