package model.condicionesYMetodologias;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.security.auth.x500.X500Principal;

import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Calculos.Calculo;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadores;

@Entity
public class CondicionConComportamiento extends Condicion {

	int cantidadDeAños= 0;
	
	private CondicionConComportamiento() {
		super();
	}

	public CondicionConComportamiento(Indicador indicador,criterioDeAceptacionDeCondicion criterio,int cantidadDeAños,String nombre){
		super(indicador,criterio,nombre);
		this.cantidadDeAños = cantidadDeAños;
	}
	
	public boolean cumpleLaCondicion(Empresa empresa, Empresa empresaNULL) {
		if(cantidadDeAños>empresa.getBalances().size()) 
			return false;
		
		List<Balance> balances = empresa.getBalances().subList(0, cantidadDeAños);
		return balances.stream().allMatch(x-> revisarComportamiento(balances,x));			
	}

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
}
