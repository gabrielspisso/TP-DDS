package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConAño;
import model.Indicador;

public class Opcion_1 implements Opcion{
	public String toString(){
		return "Tipo 1";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea mayor o menor a cierto valor,\n en el último año o durante los últimos N años";
	}

	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeAños, Calculo calculo) {
		// TODO Auto-generated method stub
		return  new CondicionConAño(indicadorActual, comportamiento, valorMinimo, cantidadDeAños);
	}
	@Override
	public boolean isVisibleCantidadDeAños() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isVisibleCalculo() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isVisibleValorMinimo() {
		// TODO Auto-generated method stub
		return true;
	}
}
