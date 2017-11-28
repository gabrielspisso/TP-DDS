package model.condicionesYMetodologias;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadoresInterfaz;

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
	
	public boolean cumpleCondicion(Empresa empresa,	RepositorioDeIndicadoresInterfaz repo){
		try{
			return this.seCumpleCondicionFiltrar(empresa, repo);
		}
		catch(IdentificadorInexistente oo)
		{
			return false;
		}
	}
	public abstract boolean seCumpleCondicionFiltrar(Empresa empresa,	RepositorioDeIndicadoresInterfaz repo);
	public String toString(){
		return nombre;
	}
	public  boolean esCondicionDeFiltrado(){
		return true;
	}
	
}
