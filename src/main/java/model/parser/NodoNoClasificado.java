package model.parser;

import model.Arbol.Hojas.Identificador;
import model.Arbol.Hojas.Numero;
import model.Arbol.Operaciones.Division;
import model.Arbol.Operaciones.Multiplicacion;
import model.Arbol.Operaciones.MultiplicacionNegativa;
import model.Arbol.Operaciones.Nodo;
import model.Arbol.Operaciones.Resta;
import model.Arbol.Operaciones.Suma;

public class NodoNoClasificado {
	private String tipo;
	private String valor;
	
	public String getValor() {
		return valor;
	}

	public NodoNoClasificado(String t, String v) {
		tipo = t;
		valor = v;
	}
	public boolean esOperacion(){
		return tipo.equals("OperadorPrimario") || tipo.equals("OperadorSecundario");
	}
	
	public boolean esParentesisDerecho() {
		return tipo.equals("ParentesisDerecho");
	}
	public boolean esParentesisIzquierdo() {
		return tipo.equals("ParentesisIzquierdo");
	}
	
	public Nodo convertirEnHoja() {
		Nodo nODO = null;
		tipoDeHoja enumval = tipoDeHoja.valueOf(tipo);
		switch(enumval){
			case Identificador:{
				nODO = new Identificador(valor);
			}break;
			case NUMERO:{
				nODO = new Numero(valor);
			}break;
		}
		return nODO;
	}
	public Nodo convertirAOperacion(){
		Nodo nuevaOperacion = null;
		if(valor.equals("*")){
			nuevaOperacion = new Multiplicacion(null, null);
		}
		else if(valor.equals("/")){
			nuevaOperacion = new Division(null, null);
		}
		else if(valor.equals("*-")){
			nuevaOperacion = new MultiplicacionNegativa(null, null);
		}
		else if(valor.equals("+")){
			nuevaOperacion = new Suma(null, null);
		}else if(valor.equals("-")){
			nuevaOperacion = new Resta(null, null);
		}
		
		return nuevaOperacion;
	}
	private enum tipoDeHoja{
		Identificador,
		NUMERO
	}
}
