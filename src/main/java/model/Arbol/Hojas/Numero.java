package model.Arbol.Hojas;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Operacion;

public class Numero extends Hoja{

	
	public Numero(String valorDeHoja) {
		super(valorDeHoja);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return Double.parseDouble(valorDeHoja);
	}

	@Override
	public boolean contieneEsteToken(String token) {
		return false;
	}
}
