package condicionesYMetodologias;

import java.util.List;

import Calculos.criterioDeAceptacionDeCondicion;
import model.Balance;
import model.Empresa;
import model.Indicador;

public class CondicionEntreDosEmpresas extends Condicion {
	
	public CondicionEntreDosEmpresas(Indicador indicador, criterioDeAceptacionDeCondicion criterio) {
		super(indicador, criterio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa2){
		return criterio.cumpleCriterioDeAceptacionDeCondicion(indicador.calcularValor(empresa2.getBalances().get(0).getCuentas()),indicador.calcularValor(empresa.getBalances().get(0).getCuentas()));
	}
}
