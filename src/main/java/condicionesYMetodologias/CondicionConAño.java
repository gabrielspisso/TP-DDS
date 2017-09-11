package condicionesYMetodologias;
import java.util.List;

import javax.persistence.Entity;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

//@Entity
public class CondicionConA�o extends CondicionUnitaria{
	double valorMinimo;
	int cantidadDeA�os;
	public CondicionConA�o(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeA�os){
		super(indicador,criterio,"");
		this.valorMinimo = valorMinimo;
		this.cantidadDeA�os = cantidadDeA�os;
		
	}
	public CondicionConA�o(ValoresParaEvaluar valores) {
		super(valores);
		this.valorMinimo = valores.getValorMinimo();
		this.cantidadDeA�os = valores.getCantidadDeA�os();
	}
	
	public boolean seCumpleLaCondicionUnitaria(Empresa empresa, Empresa empresa1){
		if(empresa.getBalances().size() < cantidadDeA�os){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeA�os);
		
			return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));				
	}
}
