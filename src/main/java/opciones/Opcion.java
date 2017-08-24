package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConA�o;
import model.Indicador;

public interface Opcion {
	
	public String toString();
	public String getDescripcion();
	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeA�os, Calculo calculo);
	public boolean isVisibleCantidadDeA�os();

	public boolean isVisibleCalculo();
	
	public boolean isVisibleValorMinimo();
}
