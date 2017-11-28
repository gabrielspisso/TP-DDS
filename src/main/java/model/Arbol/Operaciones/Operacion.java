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
import model.repositorios.RepositorioDeIndicadoresInterfaz;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Operacion extends Nodo{
	
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected Nodo izquierda;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected Nodo derecha;
	
	public Operacion(String operador, Nodo izquierda, Nodo derecha) {
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
	
	public void setIzquierda(Nodo izquierda) {
		this.izquierda = izquierda;
	}

	public void cargar(Nodo izquierda, Nodo derecha) {
		this.izquierda = izquierda;
		this.derecha = derecha;
	}
	public void setDerecha(Nodo derecha) {
		this.derecha = derecha;
	}

	@Override
	public boolean contieneEsteToken(String token, Long id,RepositorioDeIndicadoresInterfaz repo) {
		return izquierda.contieneEsteToken(token, id,repo) || derecha.contieneEsteToken(token, id,repo);
	}

	public abstract double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores,RepositorioDeIndicadoresInterfaz repo);
	
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
