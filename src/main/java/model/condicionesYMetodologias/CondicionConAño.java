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
public class CondicionConAño extends CondicionesFiltro{
	private double valorMinimo;
	private int cantidadDeAños;
	
	protected CondicionConAño()	{
		super();
	}

	public CondicionConAño(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeAños,String nombre){
		super(indicador,criterio,nombre);

		this.valorMinimo = valorMinimo;
		this.cantidadDeAños = cantidadDeAños;
	}
	
	public boolean seCumpleCondicionFiltrar(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeAños){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeAños);
		
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
	
	@Override
	public boolean cumpleLaCondicion(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeAños)
			return false;
		
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeAños);
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
}
