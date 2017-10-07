package model.condicionesYMetodologias;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import model.Balance;
import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;

@Entity
public class CondicionEntreDosEmpresas extends Condicion {

	
	private CondicionEntreDosEmpresas() {
		super();
	}

	public CondicionEntreDosEmpresas(Indicador indicador, criterioDeAceptacionDeCondicion criterio,String nombre) {
		super(nombre,indicador,criterio);
		// TODO Auto-generated constructor stub
	}

	

	public int compararEmpresas(Empresa empresa, Empresa empresa2){
			
			if(existeEseIndicadorParaEstaEmpresa(empresa,indicador)&& existeEseIndicadorParaEstaEmpresa(empresa,indicador)){
				return criterio.cumpleCriterioDeAceptacionDeCondicion(indicador.calcularValor(empresa2.getBalances().get(0).getCuentas()),indicador.calcularValor(empresa.getBalances().get(0).getCuentas()))? 1: -1;	
			}
			else if(existeEseIndicadorParaEstaEmpresa(empresa,indicador)){
				return 1;
			}
			else if(existeEseIndicadorParaEstaEmpresa(empresa2,indicador))
				return -1;
			return 0;
	}
	/*
	public String toString(){
		return "Compara si el indicador \"" +indicador.toString() +"\"de una empresa  es "+criterio.toString()+"a otra empresa";
		
		//Intente que no repita lo de indicador to string y que se ocupe la clase padre, pero a java no le importo y me tomaba la del padre.
	}
	*/

	@Override
	public boolean esCondicionDeFiltrado() {
		return false;
	}


	
}
