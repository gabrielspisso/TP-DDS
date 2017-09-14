package viewModel.opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConA�o;
import condicionesYMetodologias.ValoresParaEvaluar;
import model.Indicador;

public class Opcion_1 implements Opcion{
	public String toString(){
		return "Tipo 1";
		
	}
	public String getDescripcion(){
		return "Que un indicador sea mayor o menor a cierto valor,\n en el �ltimo a�o o durante los �ltimos N a�os";
	}

	public Condicion generarCondicion(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		return  new CondicionConA�o(valores);
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
	@Override
	public boolean isVisibleListaCondiciones() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void contenidoAdicional(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		if(valores.getCantidadDeA�os()==0)
			throw new NoItemSelectedException();
		
	}

}
