package condicionesYMetodologias;
import java.util.List;

import Calculos.criterioDeAceptacionDeCondicion;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;
public class CondicionConA�o extends Condicion{
	double valorMinimo;
	int cantidadDeA�os;
	public CondicionConA�o(double valorMinimo,int cantidadDeA�os,Indicador indicador, boolean taxatividad,criterioDeAceptacionDeCondicion criterio){
		super(indicador,taxatividad,criterio);
		this.valorMinimo = valorMinimo;
		this.cantidadDeA�os = cantidadDeA�os;
		
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeA�os){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0, cantidadDeA�os);
		return listaDeBalances.stream().allMatch(balance->criterio.cumpleCriterioDeAceptacionDeCondicion(this.valorMinimo,this.indicador.calcularValor(balance.getCuentas())));	
	}
}
