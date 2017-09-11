package condicionesYMetodologias;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Empresa;
import model.Indicador;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CondicionUnitaria extends Condicion {
	
	protected String nombre;
	protected Indicador indicador;
	
	//@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
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
