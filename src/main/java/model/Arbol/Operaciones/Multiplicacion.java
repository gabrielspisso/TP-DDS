package model.Arbol.Operaciones;

import java.util.List;

import javax.persistence.Entity;

import model.Cuenta;
import model.Indicador;
import model.repositorios.RepositorioDeIndicadoresInterfaz;
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
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores,RepositorioDeIndicadoresInterfaz repo) {
		return izquierda.calcularValor(listaDeCuentas, listaDeIndicadores,repo) *
				derecha.calcularValor(listaDeCuentas, listaDeIndicadores,repo);
	}

	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return 0;
	}
}
