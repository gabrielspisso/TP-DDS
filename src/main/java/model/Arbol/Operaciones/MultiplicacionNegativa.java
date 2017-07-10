package model.Arbol.Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public class MultiplicacionNegativa extends OperacionSecundaria{

	public MultiplicacionNegativa(Operacion izquierda, Operacion derecha) {
		super("*-", izquierda, derecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return -izquierda.calcularValor(listaDeCuentas, listaDeIndicadores) *
				derecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
}
