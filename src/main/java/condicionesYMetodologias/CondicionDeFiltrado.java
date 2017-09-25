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

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CondicionDeFiltrado extends Condicion {
	
	

	protected CondicionDeFiltrado(){

	}
	public CondicionDeFiltrado(Indicador indicador,criterioDeAceptacionDeCondicion criterio, String nombre){
		this.indicador = indicador;
		this.nombre = nombre;
		this.criterio = criterio;
	}
	public CondicionDeFiltrado(ValoresParaEvaluar valores){
		this.indicador = valores.getIndicadorActual();
		this.criterio = valores.getComportamiento();
		this.nombre = valores.getNombre();
	}
	public boolean cumpleCondicion(Empresa empresa){
		try{
			return this.seCumpleCondicionFiltrar(empresa);
		}
		catch(IdentificadorInexistente oo)
		{
			return false;
		}
	}
	public abstract boolean seCumpleCondicionFiltrar(Empresa empresa);
	public String toString(){
		return nombre;
	}
	public  boolean esCondicionDeFiltrado(){
		return true;
	}
	
}
