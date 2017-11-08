package model.condicionesYMetodologias;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import model.Balance;
import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadores;

@Entity
public class CondicionConA�o extends CondicionesFiltro{
	private double valorMinimo;
	private int cantidadDeA�os;
	
	protected CondicionConA�o()	{
		super();
	}

	public CondicionConA�o(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeA�os,String nombre){
		super(indicador,criterio,nombre);

		this.valorMinimo = valorMinimo;
		this.cantidadDeA�os = cantidadDeA�os;
	}
	
	public boolean seCumpleCondicionFiltrar(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeA�os){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeA�os);
		
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
	
	@Override
	public boolean cumpleLaCondicion(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeA�os)
			return false;
		
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeA�os);
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
}
