package condicionesYMetodologias;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

@Entity
public class CondicionConAño extends CondicionUnitaria{
	private double valorMinimo;
	private int cantidadDeAños;
	
	public CondicionConAño(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeAños,String nombre){
		super(indicador,criterio,nombre);

		this.valorMinimo = valorMinimo;
		this.cantidadDeAños = cantidadDeAños;
		
	}
	public CondicionConAño(ValoresParaEvaluar valores) {
		super(valores);
		this.valorMinimo = valores.getValorMinimo();
		this.cantidadDeAños = valores.getCantidadDeAños();
	}
	
	public boolean seCumpleLaCondicionUnitaria(Empresa empresa, Empresa empresa1){
		if(empresa.getBalances().size() < cantidadDeAños){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeAños);
		
			return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
}
