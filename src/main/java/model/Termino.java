package model;

import java.util.List;

public class Termino {
	private String signo;
	private Nodo arbolDeOperaciones;
	
	public Termino(String signo, Nodo arbolDeOperaciones) {
		this.signo = signo;
		this.arbolDeOperaciones = arbolDeOperaciones;
	}

	public double calcular(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores){
		return agregarSigno(arbolDeOperaciones.calcularValor(listaDeCuentas, listaDeIndicadores));
	}
	
	private double agregarSigno(double valor){
		return (signo.equals("+")) ? valor : -valor;
	}

	public boolean contieneEsteToken(String token) {
		return arbolDeOperaciones.contieneEsteToken(token);
	}
}
