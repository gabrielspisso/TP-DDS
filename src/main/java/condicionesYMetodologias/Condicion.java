package condicionesYMetodologias;

import model.Empresa;
import model.Indicador;

public class Condicion {
	protected Indicador indicador;
	protected boolean taxatividad;
	public Condicion(Indicador indicador,boolean taxatividad){
		this.indicador = indicador;
		this.taxatividad = taxatividad;
	}
	public boolean cumpleCondicion(Empresa empresa){
		return false;
	}
	
	public boolean getTaxatividad(){
		return taxatividad;		
	}
	
}
