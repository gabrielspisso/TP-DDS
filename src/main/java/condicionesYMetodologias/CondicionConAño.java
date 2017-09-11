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
public class CondicionConAño extends CondicionUnitaria{
	double valorMinimo;
	int cantidadDeAños;
	public CondicionConAño(Indicador indicador, criterioDeAceptacionDeCondicion criterio, double valorMinimo,int cantidadDeAños){
		super(indicador,criterio,"");
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
