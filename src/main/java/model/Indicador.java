package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
//import String.join;
import parser.PARSER;
import parser.TokenYTipo;


public class Indicador {
	String nombre;
	List<TokenYTipo> listaDeTokens;
	
	@Override
	public String toString(){
		return nombre;
	}
	
	public Indicador(List<TokenYTipo> listaDeTokens, String nombre) { //Falta agregar la de cuentas
		this.listaDeTokens = listaDeTokens;
		
		this.nombre = nombre.toString();
		mostrarFormula();
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
		return PARSER.calcularValor(nombre,listaDeTokens, listaDeCuentas, listaDeIndicadores);
	}

	public String mostrarFormula() {
		String formula = "";
		List<String> l = new ArrayList<>();
		
		listaDeTokens.stream().forEach(x->l.add(x.getValor()));
		formula = String.join("", l );

		return nombre + " = " + formula;
	}
	
	

}
