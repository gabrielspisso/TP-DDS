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
public class CondicionConAnio extends CondicionDeFiltrado{
	private CondicionConAnio() {
		super();
		// TODO Auto-generated constructor stub
	}

	private double valorMinimo;
	private int cantidadDeAnios;
	
	public CondicionConAnio(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeAnios,String nombre){
		super(indicador,criterio,nombre);

		this.valorMinimo = valorMinimo;
		this.cantidadDeAnios = cantidadDeAnios;
		
	}
	
	public boolean seCumpleCondicionFiltrar(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeAnios){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeAnios);
		
			return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
}
