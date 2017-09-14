package Calculos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Promedio extends Calculo {
	
	
	//@Transient
	private static Promedio instancia = null; 
	public static Promedio getSingletonPromedio(){
		if(instancia == null){
			instancia = new Promedio();
		}
		return instancia;
	}
	
	private Promedio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double realizarCalculo(List<Double> listaDeResultados){
		return Sumatoria.getSingletonSumatoria().realizarCalculo(listaDeResultados) / listaDeResultados.size();
	}
	@Override
	public String toString(){
		return "Promedio";
	}
}
