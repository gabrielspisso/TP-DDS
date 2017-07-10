package model.Arbol.Hojas;

import java.util.List;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Operacion;

public abstract class Hoja implements Operacion{
	protected String valorDeHoja;

	public Hoja(String valorDeHoja) {
		this.valorDeHoja = valorDeHoja;
	}

	@Override
	public String mostrarFormula() {
		return valorDeHoja;
	}

}
