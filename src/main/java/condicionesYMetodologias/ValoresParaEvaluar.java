package condicionesYMetodologias;

import Calculos.Calculo;
import model.Indicador;

public class ValoresParaEvaluar {

	Indicador indicadorActual;
	String opcion = "Default";
	Calculo calculo;
	int cantidadDeA�os;
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
	public int getCantidadDeA�os() {
		return cantidadDeA�os;
	}
	public void setCantidadDeA�os(int cantidadDeA�os) {
		this.cantidadDeA�os = cantidadDeA�os;
	}
	public int getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(int valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	
	
}
