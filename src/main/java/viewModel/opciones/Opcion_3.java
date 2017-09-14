package viewModel.opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.ValoresParaEvaluar;
import condicionesYMetodologias.condicionConCalculo;
import model.Indicador;

public class Opcion_3 implements Opcion {
	public String toString(){
		return "Tipo 3";
		
	}
	public String getDescripcion(){
		return "Que un promedio, mediana o sumatoria de un cierto indicador sea mayor o menor a cierto valor";
	}
	public Condicion generarCondicion(ValoresParaEvaluar valores) {
		return  new condicionConCalculo(valores);
	}
	@Override
	public boolean isVisibleCantidadDeAños() {
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
	@Override
	public boolean isVisibleListaCondiciones() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void contenidoAdicional(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		if(valores.getCalculo()==null)
			throw new NoItemSelectedException();
		
	}

}
