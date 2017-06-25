package model.Arbol.Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public class MULTIPLICACION extends OperacionSecundaria {

	public MULTIPLICACION(Operacion izquierda, Operacion derecha) {
		super("*", izquierda, derecha);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return izquierda.calcularValor(listaDeCuentas, listaDeIndicadores) *
				derecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
}
