package model.Builders;

import java.util.ArrayList;
import java.util.List;

import model.Termino;
import model.Arbol.Hojas.IndicadorOCuenta;
import model.Arbol.Hojas.Numero;
import model.Arbol.Operaciones.DIVISION;
import model.Arbol.Operaciones.MULTIPLICACION;
import model.Arbol.Operaciones.Operacion;
import model.Arbol.Operaciones.OperacionSecundaria;
import parser.TokenYTipo;
public class ArbolBuilder {

	private static List<TokenYTipo> listaDeTokens;
	private static List<Termino> listaDeTerminos;
	private static Termino terminoActual;
	private static Operacion arbolActual;

	public static List<Termino> Build(List<TokenYTipo> lista){
		listaDeTokens =  lista;

		arbolActual = consumirHoja();
		terminoActual = new Termino("+", arbolActual);
		
		listaDeTerminos = new ArrayList<>();
		listaDeTerminos.add(terminoActual);
		
		while(!listaDeTokens.isEmpty()){
			
			TokenYTipo token = consumirToken();
			if(esUnaOperacionPrimaria(token)){
				agregarUnNuevoTermino(token);
			}
			else{
				agregarOperacionSecundaria(token);
			}
				
		}
		return listaDeTerminos;
	}
	
	//Hay que mejorar esto
	private static void agregarOperacionSecundaria(TokenYTipo token) {
		OperacionSecundaria nuevaOperacion = null;
		if(token.getValor().equals("*")){
			nuevaOperacion = new MULTIPLICACION(arbolActual, consumirHoja());
		}
		if(token.getValor().equals("/")){
			nuevaOperacion = new DIVISION(arbolActual, consumirHoja());
		}
		arbolActual = nuevaOperacion;
		terminoActual.setArbol(arbolActual);
	}

	private static void agregarUnNuevoTermino(TokenYTipo token){
		arbolActual = consumirHoja();
		terminoActual = new Termino(token.getValor(), arbolActual);
		listaDeTerminos.add(terminoActual);
	}
	
	private static boolean esUnaOperacionPrimaria(TokenYTipo token){
		return token.getTipo().equals("OperadorPrimario");
	}
	
	private static TokenYTipo consumirToken(){
		TokenYTipo token = listaDeTokens.get(0);
		listaDeTokens.remove(0);
		return token;
	}
	
	private static Operacion consumirHoja(){
		return clasificarToken(consumirToken());
	}

	private static Operacion clasificarToken(TokenYTipo token) {
		Operacion valor = null;
		tipo enumval = tipo.valueOf(token.getTipo());
		switch(enumval){
			case Identificador:{
				valor = new IndicadorOCuenta(token.getValor());
			}break;
			case NUMERO:{
				valor = new Numero(token.getValor());
			}break;
		}
		return valor;
	}
	
	private enum tipo{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
}
