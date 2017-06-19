package model;

import java.util.List;

public interface Nodo {
	public boolean contieneEsteToken(String string);
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	public default boolean laOperacionEstaCargada(){
		return false;
	}
	public String mostrarFormula();
	public default boolean lasRamasEstanCargadas(){
		return true;
	}
}
