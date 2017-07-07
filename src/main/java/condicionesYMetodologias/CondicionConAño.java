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
	public CondicionConAño(double valorMinimo,int cantidadDeAños,Indicador indicador, boolean taxatividad,criterioDeAceptacionDeCondicion criterio){
		super(indicador,taxatividad,criterio);
		this.valorMinimo = valorMinimo;
		this.cantidadDeAños = cantidadDeAños;
		
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeAños){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0, cantidadDeAños);
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));	
	}
}
