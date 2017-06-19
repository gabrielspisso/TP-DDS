package model;

import java.util.List;


public class Raiz implements Nodo{

	Nodo ramaDerecha = null;
	Nodo ramaIzquierda = null;
	String operacion;
	public Nodo getRamaDerecha() {
		return ramaDerecha;
	}

	public void setRamaDerecha(Nodo ramaDerecha) {
		this.ramaDerecha = ramaDerecha;
	}
	
	public Nodo getRamaIzquierda() {
		return ramaIzquierda;
	}

	public void setRamaIzquierda(Nodo ramaIzquierda) {
		this.ramaIzquierda = ramaIzquierda;
	}
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		double valor = 0;
		Operacion valorDeEnum = Operacion.valueOf(operadorEscritoEnLetras());
		switch(valorDeEnum){
		case Suma: 
			valor = ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores) +
					ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores);
			break;
		case Resta: 
			valor = ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores) -
					ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores);
			break;
		case Multiplicacion: 
			valor = ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores) *
					ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores);
			break;
		case MultiplicacionNegativa:
			valor = ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores) *
			ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores) * -1;
			break;
		case Division: 
			valor = ramaDerecha.calcularValor(listaDeCuentas, listaDeIndicadores) /
					ramaIzquierda.calcularValor(listaDeCuentas, listaDeIndicadores);
			break;
		}
		
		return valor;
	}

	@Override
	public String mostrarFormula() {
		return ramaDerecha.mostrarFormula() + operacion + ramaIzquierda.mostrarFormula(); 
	}
	
	private String operadorEscritoEnLetras() {
		return 
		(operacion.equals("+")) ? "Suma" : 	(operacion.equals("-")) ? "Resta" :
		(operacion.equals("*")) ? "Multiplicacion" : (operacion.equals("*-")) ? "MultiplicacionNegativa"
				: "Division"; 
	}
	
	private enum Operacion{
		Suma,
		Resta,
		Multiplicacion,
		MultiplicacionNegativa,
		Division
	}

	@Override
	public boolean contieneEsteToken(String string) {
		return ramaDerecha.contieneEsteToken(string) || ramaIzquierda.contieneEsteToken(string);
	}
	
	public boolean lasRamasEstanCargadas(){
		return (ramaDerecha != null) || (ramaIzquierda != null);
	}
	
	public void cargarRama(Nodo nuevaRama){
		if(ramaDerecha == null) 
			ramaDerecha = nuevaRama; 
		else
			ramaIzquierda = nuevaRama;
	}
	
	
	
	public boolean laOperacionEstaCargada(){
		return operacion != null;
	}
	public void cargarOperacion(String operacion){
		this.operacion = operacion;
	}
}
