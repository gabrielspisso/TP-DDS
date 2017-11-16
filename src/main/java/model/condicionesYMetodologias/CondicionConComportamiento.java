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
public class CondicionConComportamiento extends CondicionDeFiltrado {

	int cantidadDeAnios= 0;
	
	public CondicionConComportamiento(Indicador indicador,criterioDeAceptacionDeCondicion criterio,int cantidadDeAnios,String nombre){
		super(indicador,criterio,nombre);
		this.cantidadDeAnios = cantidadDeAnios;
	}
	
	

	private CondicionConComportamiento() {
		super();
	}



	@Override
	
	public boolean seCumpleCondicionFiltrar(Empresa empresa){
		if(cantidadDeAnios>empresa.getBalances().size()) 
			return false;
		
		List<Balance> balances = empresa.getBalances().subList(0, cantidadDeAnios);
		
		return balances.stream().allMatch(x-> revisarComportamiento(balances,x));			
		
	}
	/*
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		
		
		List<Balance> balances = empresa.getBalances().subList(0, (cantidadDeAnios>empresa.getBalances().size()?cantidadDeAnios:getBalances().size()));
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
		return indicador.toString()+ "es " + criterio.toString() +" durante "+ cantidadDeAnios;
		
	}
	*/
}
