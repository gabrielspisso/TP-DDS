package Calculos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public abstract class Calculo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	protected Calculo() {}
	public abstract double realizarCalculo(List<Double> listaDeResultados);
}
