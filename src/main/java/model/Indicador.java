package model;
import java.util.List;



public class Indicador {
	private String nombre;
	private Nodo arbolDeOperaciones; 
	
	@Override
	public String toString(){
		return nombre;
	}
	
	
	public Indicador(String nombre, Nodo arbolDeOperaciones) {
		this.nombre = nombre;
		this.arbolDeOperaciones = arbolDeOperaciones;
	}

	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return arbolDeOperaciones.calcularValor(listaDeCuentas, listaDeIndicadores);
	}

	public String mostrarFormula() {
		return arbolDeOperaciones.mostrarFormula();
	}


	public String getNombre() {
		return nombre;
	}


	public boolean contieneEsteToken(String token) {
		return arbolDeOperaciones.contieneEsteToken(token);
	}
	
	

}
