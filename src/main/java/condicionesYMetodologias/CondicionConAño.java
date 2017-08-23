package condicionesYMetodologias;
import java.util.List;

import Calculos.criterioDeAceptacionDeCondicion;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;
public class CondicionConAño extends Condicion{
	double valorMinimo;
	int cantidadDeAños;
	public CondicionConAño(double valorMinimo,int cantidadDeAños,Indicador indicador,criterioDeAceptacionDeCondicion criterio){
		super(indicador,criterio);
		this.valorMinimo = valorMinimo;
		this.cantidadDeAños = cantidadDeAños;
		
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		if(empresa.getBalances().size() < cantidadDeAños){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0,cantidadDeAños);
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));	
	}
	@Override
	public String toString(){
		return indicador.toString()+" es "+criterio.toString()+" a "+ valorMinimo + " durante "+cantidadDeAños;
		
		//Intente que no repita lo de indicador to string y que se ocupe la clase padre, pero a java no le importo y me tomaba la del padre.
	}
}
