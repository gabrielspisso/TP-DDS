package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.condicionConCalculo;
import model.Indicador;

public class Opcion_3 implements Opcion {
	public String toString(){
		return "Tipo 3";
		
	}
	public String getDescripcion(){
		return "Que un promedio, mediana o sumatoria de un cierto indicador sea mayor o menor a cierto valor";
	}
	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeA�os, Calculo calculo) {
		return  new condicionConCalculo(indicadorActual, comportamiento, calculo, valorMinimo);
	}
	@Override
	public boolean isVisibleCantidadDeA�os() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isVisibleCalculo() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isVisibleValorMinimo() {
		// TODO Auto-generated method stub
		return true;
	}

}
