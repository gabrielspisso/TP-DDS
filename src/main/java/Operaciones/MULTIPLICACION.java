package Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Nodo;

public class MULTIPLICACION {
	public static double calcular(Nodo ramaIzquierda, Nodo ramaDerecha, List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		double valor = ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores) *
				ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores);
		return  ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores) *
				ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
}
