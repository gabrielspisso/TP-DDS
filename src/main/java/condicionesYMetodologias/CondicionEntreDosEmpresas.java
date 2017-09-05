package condicionesYMetodologias;

import java.util.List;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;

public class CondicionEntreDosEmpresas extends CondicionUnitaria {
	
	public CondicionEntreDosEmpresas(Indicador indicador, criterioDeAceptacionDeCondicion criterio) {
		super(indicador, criterio,"");
		// TODO Auto-generated constructor stub
	}

	public CondicionEntreDosEmpresas(ValoresParaEvaluar valores) {
		super(valores);
	}

	@Override
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
