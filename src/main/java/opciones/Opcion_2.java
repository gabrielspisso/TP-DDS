package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionEntreDosEmpresas;
import condicionesYMetodologias.ValoresParaEvaluar;
import model.Indicador;

public class Opcion_2 implements Opcion{
	public String toString(){
		return "Tipo 2";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea mayor o menor que el de otra empresa";
	}

	public Condicion generarCondicion(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		return new CondicionEntreDosEmpresas(valores);
	}
	@Override
	public boolean isVisibleCantidadDeAños() {
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
