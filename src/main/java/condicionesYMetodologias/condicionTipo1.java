package condicionesYMetodologias;
import java.util.List;

import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;
public class condicionTipo1 extends Condicion{
	int valorMinimo;
	int cantidadDeA�os;
	public condicionTipo1(int valorMinimo,int cantidadDeA�os,Indicador indicador){
		super(indicador);
		this.valorMinimo = valorMinimo;
		this.cantidadDeA�os = cantidadDeA�os;		
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		if(empresa.getBalances().size() < cantidadDeA�os){
			return false;
		}
		List<Balance> listaDeBalances = empresa.getBalances().subList(0, cantidadDeA�os);
		return listaDeBalances.stream().allMatch(balance->this.valorMinimo <=indicador.calcularValor(balance.getCuentas()));	
	}
}
