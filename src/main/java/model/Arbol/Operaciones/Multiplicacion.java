package model.Arbol.Operaciones;

import java.util.List;

import javax.persistence.Entity;

import model.Cuenta;
import model.Indicador;
@Entity
public class Multiplicacion extends Operacion {

	protected Multiplicacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Multiplicacion(Nodo izquierda, Nodo derecha) {
		super("*", izquierda, derecha);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return izquierda.calcularValor(listaDeCuentas, listaDeIndicadores) *
				derecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}

	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return 0;
	}
}
