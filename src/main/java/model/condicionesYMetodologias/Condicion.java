package model.condicionesYMetodologias;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadoresInterfaz;

@MappedSuperclass
public abstract class Condicion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	protected String nombre;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected Indicador indicador;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected criterioDeAceptacionDeCondicion criterio;

	
	protected Condicion(){
		
	}
	
	protected Condicion(String nombre, Indicador indicador, criterioDeAceptacionDeCondicion criterio){
		this.nombre = nombre;
		this.indicador = indicador;
		this.criterio = criterio;
	}
	

	public abstract boolean esCondicionDeFiltrado();
	
	
	@Override
	public String toString(){
		return nombre;
	}
	protected boolean existeEseIndicadorParaEstaEmpresa(Empresa empresa, Indicador indicador,RepositorioDeIndicadoresInterfaz repositorio){
		try{
			indicador.calcularValor(empresa.getBalances().get(0).getCuentas(),repositorio);
			return true;
		}
		catch(RuntimeException ex){
			return false;
		}
	}

	public abstract boolean cumpleCondicion(Empresa empresa,	RepositorioDeIndicadoresInterfaz repo);
}
