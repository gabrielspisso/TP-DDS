package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConA�o;
import model.Indicador;

public class Opcion_1 implements Opcion{
	public String toString(){
		return "Tipo 1";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea mayor o menor a cierto valor,\n en el �ltimo a�o o durante los �ltimos N a�os";
	}

	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeA�os, Calculo calculo) {
		// TODO Auto-generated method stub
		return  new CondicionConA�o(indicadorActual, comportamiento, valorMinimo, cantidadDeA�os);
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
		return true;
	}
}
