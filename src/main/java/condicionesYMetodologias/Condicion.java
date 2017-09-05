package condicionesYMetodologias;

import Calculos.criterioDeAceptacionDeCondicion;
import model.Empresa;
import model.Indicador;

public abstract class Condicion {
	protected String nombre;
	protected Indicador indicador;
	protected criterioDeAceptacionDeCondicion criterio;

	
	public Condicion(Indicador indicador,criterioDeAceptacionDeCondicion criterio, String nombre){
		this.indicador = indicador;
		this.nombre = nombre;
		this.criterio = criterio;
	}
	public Condicion(ValoresParaEvaluar valores){
		this.indicador = valores.getIndicadorActual();
		this.criterio = valores.getComportamiento();
		this.nombre = valores.getNombre();
	}
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa2){
		return false;
	}

	public String toString(){
		return nombre;
	}
	
	
}
