package model.Arbol.Operaciones;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public interface NODO {
	public boolean contieneEsteToken(String token);
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	public String mostrarFormula();
	public boolean estaCargado();
	public boolean esOperacion();
	public String valor();
}
