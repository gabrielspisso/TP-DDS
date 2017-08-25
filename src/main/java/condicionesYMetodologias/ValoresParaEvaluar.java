package condicionesYMetodologias;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import model.Indicador;
import opciones.Opcion;

public class ValoresParaEvaluar {

	Indicador indicadorActual;
	Opcion opcion = null;
	Calculo calculo;
	int cantidadDeA�os;
	int valorMinimo;
	String nombre;
	criterioDeAceptacionDeCondicion comportamiento;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public criterioDeAceptacionDeCondicion getComportamiento() {
		return comportamiento;
	}
	public void setComportamiento(criterioDeAceptacionDeCondicion comportamiento) {
		this.comportamiento = comportamiento;
	}
	public Indicador getIndicadorActual() {
		return indicadorActual;
	}
	public void setIndicadorActual(Indicador indicadorActual) {
		this.indicadorActual = indicadorActual;
	}
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {

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
