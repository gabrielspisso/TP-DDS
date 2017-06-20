package Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Nodo;

public class Operacion {
	static List<Cuenta> listaDeCuentas;
	static List<Indicador> listaDeIndicadores;
	public Operacion(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		this.listaDeCuentas = listaDeCuentas;
		this.listaDeIndicadores = listaDeIndicadores;
	}
	
	
	
	public double calcular(String operacion, Nodo ramaIzquierda, Nodo ramaDerecha){
		if(ramaIzquierda == null)
			return ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores);
		if(ramaDerecha == null)
			return ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores);
		
		if(operacion.equals("*"))
			return MULTIPLICACION.calcular(ramaIzquierda, ramaDerecha, listaDeCuentas, listaDeIndicadores);
		
		if(operacion.equals("/"))
			return DIVISION.calcular(ramaIzquierda, ramaDerecha, listaDeCuentas, listaDeIndicadores);
		
		return 0;
	}
}
