package Calculos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Sumatoria extends Calculo{
	
	//@Transient
	private static Sumatoria instancia = null; 
	public static Sumatoria getSingletonSumatoria(){
		if(instancia == null){
			instancia = new Sumatoria();
		}
		return instancia;
	}
	
	private Sumatoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Transient
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
