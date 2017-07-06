package condicionesYMetodologias;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import Calculos.Calculo;
import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class CondicionTipo4 extends Condicion {

	String comportamiento;
	public CondicionTipo4(String comportamiento, Indicador indicador){
		super(indicador);
		this.comportamiento = comportamiento;
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		int posicionActual = 0;
		List<Balance> balances = empresa.getBalances();
		return empresa.getBalances().stream().allMatch(x-> revisarComportamiento(balances,posicionActual));
		
		//Esto no anda
	}
	public boolean revisarComportamiento(List<Balance> balances, int posicion){
		if(posicion == balances.size()-1){
			return true;
		}
		double res1 = indicador.calcularValor(balances.get(0).getCuentas());
		double res2 = indicador.calcularValor(balances.get(1).getCuentas());
		if(comportamiento.equals("Creciente")){ //Se repite logica, esta hecho  rapido.
			return res1 <= res2 ;
		}
		else{
			return res1 >= res2;
		}
		
	}
}
