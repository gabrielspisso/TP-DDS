package model.Builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
		while(!listaDeTokens.isEmpty()){ //Deberia ser mas decente, con un foreach o algo.
			
			TokenYTipo token = consumirToken();
			if(token.esOperacionPrimaria()){
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
		
		arbolActual = token.convertirAOperacionSecundaria(arbolActual, consumirHoja());
		terminoActual.setArbol(arbolActual);
	}

	private static void agregarUnNuevoTermino(TokenYTipo token){
		arbolActual = consumirHoja();
		terminoActual = new Termino(token.getValor(), arbolActual);
		listaDeTerminos.add(terminoActual);
	}
	
	
	
	private static TokenYTipo consumirToken(){
		TokenYTipo token = listaDeTokens.get(0);
		listaDeTokens.remove(0);
		return token;
	}
	
	private static Operacion consumirHoja(){
		return consumirToken().convertirEnHoja();
	}


}
