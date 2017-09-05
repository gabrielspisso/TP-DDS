package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConA�o;
import condicionesYMetodologias.ValoresParaEvaluar;
import model.Indicador;

public interface Opcion {
	
	public String toString();
	public String getDescripcion();
	public Condicion generarCondicion(ValoresParaEvaluar valores);
	public boolean isVisibleCantidadDeA�os();

	public boolean isVisibleCalculo();
	
	public boolean isVisibleValorMinimo();
	public boolean isVisibleListaCondiciones();
}
