package condicionesYMetodologias;
import java.util.List;

import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;
public class condicionTipo1 extends Condicion{
	int valorMinimo;
	int cantidadDeAños;
	public condicionTipo1(int valorMinimo,int cantidadDeAños,Indicador indicador){
		super(indicador);
		this.valorMinimo = valorMinimo;
		this.cantidadDeAños = cantidadDeAños;		
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeAños){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0, cantidadDeAños);
		return listaDeBalances.stream().allMatch(balance->this.valorMinimo <=indicador.calcularValor(balance.getCuentas()));	
	}
}
