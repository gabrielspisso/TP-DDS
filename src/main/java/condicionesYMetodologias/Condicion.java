package condicionesYMetodologias;

import Calculos.criterioDeAceptacionDeCondicion;
import model.Empresa;
import model.Indicador;

public class Condicion {
	protected Indicador indicador;
	protected criterioDeAceptacionDeCondicion criterio;
//	protected String nombre;
//	protected String tipo;
//	protected String dataInterna;	
	
	public Condicion(Indicador indicador,criterioDeAceptacionDeCondicion criterio){
		this.indicador = indicador;
		this.criterio = criterio;
	}
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa2){
		return false;
	}
	
	
}
