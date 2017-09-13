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
public class CondicionConA�o extends Condicion{
	private double valorMinimo;
	private int cantidadDeA�os;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Indicador indicador;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private criterioDeAceptacionDeCondicion criterio;
	
	public CondicionConA�o(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeA�os,String nombre){
		super(nombre);
		this.indicador = indicador;
		this.criterio = criterio; 
		this.valorMinimo = valorMinimo;
		this.cantidadDeA�os = cantidadDeA�os;
		
	}
	public CondicionConA�o(ValoresParaEvaluar valores) {
		super(valores.getNombre());
		this.indicador = valores.getIndicadorActual();
		this.criterio = valores.getComportamiento(); 
		this.valorMinimo = valores.getValorMinimo();
		this.cantidadDeA�os = valores.getCantidadDeA�os();
	}
	
	public boolean seCumpleLaCondicion(Empresa empresa, Empresa empresa1){
		if(empresa.getBalances().size() < cantidadDeA�os){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeA�os);
		
			return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
}
