package model;

import java.util.List;

public interface Nodo {
	public boolean contieneEsteToken(String string);
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	public String mostrarFormula();
}
