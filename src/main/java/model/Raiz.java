package model;

import java.util.List;


public class Raiz implements Nodo{

	Nodo ramaDerecha = null;
	Nodo ramaIzquierda = null;
	String operacion;
	
	
	public Raiz(Nodo ramaDerecha, Nodo ramaIzquierda, String operacion) {
		this.ramaDerecha = ramaDerecha;
		this.ramaIzquierda = ramaIzquierda;
		this.operacion = operacion;
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		if(ramaDerecha == null)
			return ((NodoCalculable)ramaIzquierda).calcularValor(listaDeCuentas, listaDeIndicadores);
		if(ramaIzquierda == null)
			return ((NodoCalculable)ramaDerecha).calcularValor(listaDeCuentas, listaDeIndicadores);
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
	public boolean contieneEsteToken(String token) {
		if(ramaDerecha == null) {
			return ((NodoCalculable)ramaIzquierda).contieneEsteToken(token);
		}
		else if(ramaIzquierda == null){
			return ((NodoCalculable)ramaDerecha).contieneEsteToken(token);
		}
		else
			return ramaDerecha.contieneEsteToken(token) || ramaIzquierda.contieneEsteToken(token);
	}
	
	public void cargarValor(Nodo nuevaRama){
		if(ramaDerecha == null) 
			ramaDerecha = nuevaRama; 
		else
			ramaIzquierda = nuevaRama;
	}
	
	
	@Override
	public boolean laOperacionEstaCargada(){
		return operacion != null;
	}
	public void cargarOperacion(String operacion){
		this.operacion = operacion;
	}

	public Raiz cargarOperacionPrimaria(String nuevaOperacion) {
		moverArbolActualALaRamaIzquierda(nuevaOperacion);
		
		operacion = nuevaOperacion;
		this.ramaDerecha = new Raiz(null, null, null);
		return (Raiz) ramaDerecha;
	}

	//Este metodo carga una operacion secundaria y retorna el lugar donde debe seguir almacenando valores
	public Raiz cargarOperacionSecundaria(String nuevaOperacion) {
		if(this.laOperacionEstaCargada()){
			moverArbolActualALaRamaIzquierda(nuevaOperacion);
			this.ramaDerecha = new Raiz(null, null, null);
			operacion = nuevaOperacion;
			return (Raiz) ramaDerecha;
		}
		else{
			operacion = nuevaOperacion;
			return this;
		}
	}
	
	private void moverArbolActualALaRamaIzquierda(String nuevaOperacion){
		Raiz nuevaRamaIzquierda = new Raiz(ramaDerecha, ramaIzquierda, operacion);
		this.ramaIzquierda = nuevaRamaIzquierda;
	}
	
}
