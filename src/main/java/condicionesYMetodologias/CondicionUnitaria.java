package condicionesYMetodologias;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Empresa;
import model.Indicador;

public abstract class CondicionUnitaria implements Condicion{
	protected String nombre;
	protected Indicador indicador;
	protected criterioDeAceptacionDeCondicion criterio;

	
	public CondicionUnitaria(Indicador indicador,criterioDeAceptacionDeCondicion criterio, String nombre){
		this.indicador = indicador;
		this.nombre = nombre;
		this.criterio = criterio;
	}
	public CondicionUnitaria(ValoresParaEvaluar valores){
		this.indicador = valores.getIndicadorActual();
		this.criterio = valores.getComportamiento();
		this.nombre = valores.getNombre();
	}
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa2){
		try{
			return this.seCumpleLaCondicionUnitaria(empresa, empresa2);
		}
		catch(IdentificadorInexistente oo)
		{
			return false;
		}
	}
	public abstract boolean seCumpleLaCondicionUnitaria(Empresa empresa, Empresa empresa2);
	public String toString(){
		return nombre;
	}
	
	
}
