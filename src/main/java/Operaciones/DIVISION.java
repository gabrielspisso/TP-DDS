package Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Nodo;

public class DIVISION {
	public static double calcular(Nodo ramaIzquierda, Nodo ramaDerecha, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		return ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores) /
				ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
}
