package opciones;

import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConComportamiento;
import condicionesYMetodologias.ValoresParaEvaluar;
import condicionesYMetodologias.condicionCompuesta;

public class Opcion_Compuesta implements Opcion{
	public String toString(){
		return "Tipo Composicion";
		
	}
	public String getDescripcion(){
		return "Opcion para componer las opciones";
	}

	public Condicion generarCondicion(ValoresParaEvaluar valores) {
		// TODO Auto-generated method stub
		return  new condicionCompuesta(valores);
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
	@Override
	public boolean isVisibleListaCondiciones() {
		// TODO Auto-generated method stub
		return true;
	}
}
