package model;

import java.util.List;

import model.Arbol.Operaciones.Operacion;

public class Termino implements Operacion{

	String signo;
	Operacion arbol;

	public void setArbol(Operacion arbol) {
		this.arbol = arbol;
	}

	public Termino(String signo, Operacion arbol) {
		super();
		this.signo = signo;
		this.arbol = arbol;
	}

	@Override
	public boolean contieneEsteToken(String token) {
		return arbol.contieneEsteToken(token);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return (signo.equals("+")) ? arbol.calcularValor(listaDeCuentas, listaDeIndicadores)
				: -arbol.calcularValor(listaDeCuentas, listaDeIndicadores);
	}

	@Override
	public String mostrarFormula() {
		return signo + arbol.mostrarFormula();
	}
	
	public String mostrarFormulaSinSigno(){
		return arbol.mostrarFormula();
	}

}
