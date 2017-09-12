package condicionesYMetodologias;

import java.util.ArrayList;
import java.util.List;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import model.Indicador;
import opciones.Opcion;

public class ValoresParaEvaluar {

	private Indicador indicadorActual;
	private Opcion opcion = null;
	private Calculo calculo;
	private int cantidadDeAños;
	private int valorMinimo;
	private String nombre;
	private criterioDeAceptacionDeCondicion comportamiento;
	List<Condicion> listaDeCondiciones = new ArrayList<Condicion>();
	public List<Condicion> getListaDeCondiciones() {
		return listaDeCondiciones;
	}
	public void setListaDeCondiciones(List<Condicion> listaDeCondiciones) {
		this.listaDeCondiciones = listaDeCondiciones;
	}
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
