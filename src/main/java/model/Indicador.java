package model;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import parser.PARSER;
import parser.TokenYTipo;


public class Indicador {
	String nombre;
	int valor;
	List<TokenYTipo> listaDeTokens;
	
	@Override
	public String toString(){
		return nombre;
	}
	
	public Indicador(List<TokenYTipo> listaDeTokens, String nombre) { //Falta agregar la de cuentas
		this.listaDeTokens = listaDeTokens;
		this.nombre = nombre.toString();
	}
	
	public List<TokenYTipo> getListaDeTokens() {
		return listaDeTokens;
	}

	public void recibirToken(TokenYTipo token){
		listaDeTokens.add(token);
	}
	
	public String getNombre(){
		return nombre;
	}

	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return PARSER.calcularValor(listaDeTokens, listaDeCuentas, listaDeIndicadores);
	}
	
	

}
