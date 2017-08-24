package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.Indicador;

public class Opcion_2 implements Opcion{
	public String toString(){
		return "Tipo 2";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea mayor o menor que el de otra empresa";
	}

	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeA�os, Calculo calculo) {
		// TODO Auto-generated method stub
		return new CondicionEntreDosEmpresas(indicadorActual,comportamiento);
	}
	@Override
	public boolean isVisibleCantidadDeA�os() {
		// TODO Auto-generated method stub
		return false;
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
