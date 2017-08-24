package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConComportamiento;
import model.Indicador;

public class Opcion_4 implements Opcion {
	public String toString(){
		return "Tipo 4";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea sea siempre o casi siempre creciente o decreciente durante un per�odo";
	}

	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeA�os, Calculo calculo) {
		// TODO Auto-generated method stub
		return  new CondicionConComportamiento(indicadorActual, comportamiento, cantidadDeA�os);
	}
	@Override
	public boolean isVisibleCantidadDeA�os() {
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
		return false;
	}
}
