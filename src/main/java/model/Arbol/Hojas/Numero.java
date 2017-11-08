package model.Arbol.Hojas;

import java.util.List;

import javax.persistence.Entity;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Nodo;
@Entity
public class Numero extends Operando{

	
	protected Numero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Numero(String valorDeHoja) {
		super(valorDeHoja);
	}

	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return Double.parseDouble(valor);
	}
}
