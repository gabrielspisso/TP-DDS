package model.Arbol.Operaciones;

import java.util.List;

import javax.persistence.Entity;

import model.Cuenta;
import model.Indicador;
@Entity
public class Division extends Operacion{

	protected Division() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Division(Nodo izquierda, Nodo derecha) {
		super("/", izquierda, derecha);
	}
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return izquierda.calcularValor(listaDeCuentas, listaDeIndicadores) /
				derecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return 0;
	}
}
