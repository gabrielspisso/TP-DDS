package condicionesYMetodologias;

import Calculos.criterioDeAceptacionDeCondicion;
import model.Empresa;
import model.Indicador;

public class Condicion {
	protected Indicador indicador;
	protected boolean taxatividad;

	protected criterioDeAceptacionDeCondicion criterio;
	public Condicion(Indicador indicador,boolean taxatividad,criterioDeAceptacionDeCondicion criterio){
		this.indicador = indicador;
		this.taxatividad = taxatividad;
		this.criterio = criterio;
	}
	public boolean cumpleCondicion(Empresa empresa){
		return false;
	}
	
	public boolean getTaxatividad(){
		return taxatividad;		
	}
	
}
