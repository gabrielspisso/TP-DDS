package model.Arbol.Hojas;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.NODO;

public class Numero extends Hoja{

	
	public Numero(String valorDeHoja) {
		super(valorDeHoja);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return Double.parseDouble(valor);
	}
}
