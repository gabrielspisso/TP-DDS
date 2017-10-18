package model.Arbol.Operaciones;

import java.util.List;

import javax.persistence.Entity;

import model.Cuenta;
import model.Indicador;
@Entity
public class Suma extends Operacion{

	protected Suma() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Suma(Raiz izquierda, Raiz derecha) {
		super("+", izquierda, derecha);
	}
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return izquierda.calcularValor(listaDeCuentas, listaDeIndicadores) +
				derecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return 1;
	}
}
