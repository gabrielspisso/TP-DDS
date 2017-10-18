package model.Arbol.Operaciones;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import model.Cuenta;
import model.Indicador;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Operacion extends Raiz{
	
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected Raiz izquierda;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected Raiz derecha;
	
	public Operacion(String operador, Raiz izquierda, Raiz derecha) {
		super();
		this.valor = operador;
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	public String valor() {
		return valor;
	};
	
	@Override
	public boolean estaCargado() {
		return izquierda != null && derecha != null;
	}
	
	public void setIzquierda(Raiz izquierda) {
		this.izquierda = izquierda;
	}

	public void cargar(Raiz izquierda, Raiz derecha) {
		this.izquierda = izquierda;
		this.derecha = derecha;
	}
	public void setDerecha(Raiz derecha) {
		this.derecha = derecha;
	}

	@Override
	public boolean esOperacion() {
		return true;
	}
	@Override
	public boolean contieneEsteToken(String token, Long id) {
		return izquierda.contieneEsteToken(token, id) || derecha.contieneEsteToken(token, id);
	}

	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	
	public abstract int prioridad();
	
	@Override
	public String mostrarFormula() {
		return izquierda.mostrarFormula() + valor + derecha.mostrarFormula();
	}

	protected Operacion() {
		super();
		// TODO Auto-generated constructor stub
	}
}
