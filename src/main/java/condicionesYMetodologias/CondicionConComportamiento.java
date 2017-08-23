package condicionesYMetodologias;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class CondicionConComportamiento extends Condicion {

	int cantidadDeAños= 0;
	public CondicionConComportamiento(Indicador indicador,criterioDeAceptacionDeCondicion criterio){
		super(indicador,criterio);
	}
	
	@Override
	
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa1){
		int posicionActual = 0;
		List<Balance> balances = empresa.getBalances();
		return empresa.getBalances().stream().allMatch(x-> revisarComportamiento(balances,posicionActual));
	}
	/*
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		
		
		List<Balance> balances = empresa.getBalances().subList(0, (cantidadDeAños>empresa.getBalances().size()?cantidadDeAños:getBalances().size()));
		return empresa.all(x-> revisarComportamiento(balances,posicionActual))
	}
	*/
	public boolean revisarComportamiento(List<Balance> balances, int posicion){
		if(posicion == balances.size()-1){
			return true;
		}
		double res1 = indicador.calcularValor(balances.get(posicion).getCuentas());
		double res2 = indicador.calcularValor(balances.get(posicion+1).getCuentas());
		return criterio.cumpleCriterioDeAceptacionDeCondicion(res1, res2);
	}
	@Override
	public String toString(){
		return indicador.toString()+ "es " + criterio.toString() +" durante "+ cantidadDeAños;
		
	}
}
