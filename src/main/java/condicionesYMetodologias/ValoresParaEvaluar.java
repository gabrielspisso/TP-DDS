package condicionesYMetodologias;

import Calculos.Calculo;
import model.Indicador;

public class ValoresParaEvaluar {

	Indicador indicadorActual;
	String opcion = "Default";
	Calculo calculo;
	int cantidadDeAños;
	int valorMinimo;
	
	public Indicador getIndicadorActual() {
		return indicadorActual;
	}
	public void setIndicadorActual(Indicador indicadorActual) {
		this.indicadorActual = indicadorActual;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		System.out.println("sfasdf: "+opcion);
		this.opcion = opcion;
	}
	public Calculo getCalculo() {
		return calculo;
	}
	public void setCalculo(Calculo calculo) {
		this.calculo = calculo;
	}
	public int getCantidadDeAños() {
		return cantidadDeAños;
	}
	public void setCantidadDeAños(int cantidadDeAños) {
		this.cantidadDeAños = cantidadDeAños;
	}
	public int getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(int valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	
	
}
