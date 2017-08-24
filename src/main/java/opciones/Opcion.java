package opciones;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConAño;
import model.Indicador;

public interface Opcion {
	
	public String toString();
	public String getDescripcion();
	public Condicion generarCondicion(Indicador indicadorActual, criterioDeAceptacionDeCondicion comportamiento,
			double valorMinimo, int cantidadDeAños, Calculo calculo);
	public boolean isVisibleCantidadDeAños();

	public boolean isVisibleCalculo();
	
	public boolean isVisibleValorMinimo();
}
