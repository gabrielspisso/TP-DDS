package condicionesYMetodologias;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class CondicionConComportamiento extends Condicion {

	int cantidadDeA�os= 0;
	public CondicionConComportamiento(Indicador indicador,criterioDeAceptacionDeCondicion criterio,int cantidadDeA�os){
		super(indicador,criterio,"");
		this.cantidadDeA�os = cantidadDeA�os;
	}
	
	public CondicionConComportamiento(ValoresParaEvaluar valores) {
		super(valores);
		this.cantidadDeA�os = valores.getCantidadDeA�os();
	}

	@Override
	
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa1){
		List<Balance> balances = empresa.getBalances().subList(0, (cantidadDeA�os>empresa.getBalances().size()?cantidadDeA�os:empresa.getBalances().size()));
		try{
			return balances.stream().allMatch(x-> revisarComportamiento(balances,x));			
		}
		catch(IdentificadorInexistente id){
			return false;
		}
	}
	/*
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		
		
		List<Balance> balances = empresa.getBalances().subList(0, (cantidadDeA�os>empresa.getBalances().size()?cantidadDeA�os:getBalances().size()));
		return empresa.all(x-> revisarComportamiento(balances,posicionActual))
	}
	*/
	public boolean revisarComportamiento(List<Balance> balances, Balance balance){
		if(balances.isEmpty()) return false;
		int posicion = balances.indexOf(balance);
		
		
		if(posicion == balances.size()-1){
			return true;
		}
		double res1 = indicador.calcularValor(balance.getCuentas());
		double res2 = indicador.calcularValor(balances.get(posicion+1).getCuentas());
		return criterio.cumpleCriterioDeAceptacionDeCondicion(res1, res2);
	}
	/*@Override
	public String toString(){
		return indicador.toString()+ "es " + criterio.toString() +" durante "+ cantidadDeA�os;
		
	}
	*/
}
