package parser;

import model.Arbol.Hojas.IndicadorOCuenta;
import model.Arbol.Hojas.Numero;
import model.Arbol.Operaciones.DIVISION;
import model.Arbol.Operaciones.MULTIPLICACION;
import model.Arbol.Operaciones.Operacion;

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
	public boolean esOperacionPrimaria(){
		return tipo.equals("OperadorPrimario");
	}
	
	public Operacion convertirEnHoja() {
		Operacion operacion = null;
		tipoDeHoja enumval = tipoDeHoja.valueOf(tipo);
		switch(enumval){
			case Identificador:{
				operacion = new IndicadorOCuenta(valor);
			}break;
			case NUMERO:{
				operacion = new Numero(valor);
			}break;
		}
		return operacion;
	}
	public Operacion convertirAOperacionSecundaria(Operacion ramaDerecha,Operacion ramaIzquierda){
		Operacion nuevaOperacion = null;
		if(valor.equals("*")){
			nuevaOperacion = new MULTIPLICACION(ramaDerecha, ramaIzquierda);
		}
		else if(valor.equals("/")){
			nuevaOperacion = new DIVISION(ramaDerecha, ramaIzquierda);
		}
		return nuevaOperacion;
	}
	private enum tipoDeHoja{
		Identificador,
		NUMERO
	}
}
