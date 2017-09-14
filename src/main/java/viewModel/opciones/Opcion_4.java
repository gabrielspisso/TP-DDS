package viewModel.opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConComportamiento;
import condicionesYMetodologias.ValoresParaEvaluar;
import model.Indicador;

public class Opcion_4 implements Opcion {
	public String toString(){
		return "Tipo 4";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea sea siempre o casi siempre creciente o decreciente durante un período";
	}

	public Condicion generarCondicion(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		return  new CondicionConComportamiento(valores);
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
		return false;
	}
	@Override
	public boolean isVisibleListaCondiciones() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void contenidoAdicional(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		if(valores.getCantidadDeAños()==0)
			throw new NoItemSelectedException();
		
	}
}
