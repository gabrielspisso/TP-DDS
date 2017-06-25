package model.Arbol.Hojas;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Operacion;

public class Hoja implements Operacion{
	protected String valorDeHoja;

	public Hoja(String valorDeHoja) {
		this.valorDeHoja = valorDeHoja;
	}

	@Override
	public boolean contieneEsteToken(String token) {
		return false;
	}
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return 0;
	}
	@Override
	public String mostrarFormula() {
		return valorDeHoja;
	}

}
