package condicionesYMetodologias;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;

@Entity
public class CondicionEntreDosEmpresas extends CondicionUnitaria {

	
	private CondicionEntreDosEmpresas() {
		super();
	}

	public CondicionEntreDosEmpresas(Indicador indicador, criterioDeAceptacionDeCondicion criterio,String nombre) {
		super(indicador,criterio,nombre);
		// TODO Auto-generated constructor stub
	}

	public CondicionEntreDosEmpresas(ValoresParaEvaluar valores) {
		super(valores);
		
	}

	public boolean seCumpleLaCondicionUnitaria(Empresa empresa, Empresa empresa2){
		

			return criterio.cumpleCriterioDeAceptacionDeCondicion(indicador.calcularValor(empresa2.getBalances().get(0).getCuentas()),indicador.calcularValor(empresa.getBalances().get(0).getCuentas()));			
	}
	/*
	public String toString(){
		return "Compara si el indicador \"" +indicador.toString() +"\"de una empresa  es "+criterio.toString()+"a otra empresa";
		
		//Intente que no repita lo de indicador to string y que se ocupe la clase padre, pero a java no le importo y me tomaba la del padre.
	}
	*/


	
}
