package viewModel.opciones;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConComportamiento;
import condicionesYMetodologias.ValoresParaEvaluar;

public class Opcion_Compuesta implements Opcion{
	public String toString(){
		return "Tipo Composicion";
		
	}
	public String getDescripcion(){
		return "Opcion para componer las opciones";
	}

	public Condicion generarCondicion(ValoresParaEvaluar valores) {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isVisibleCantidadDeA˝os() {
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
	@Override
	public boolean isVisibleListaCondiciones() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void contenidoAdicional(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		if(valores.getListaDeCondiciones().isEmpty())
			throw new NoItemSelectedException();
		
	}
}
