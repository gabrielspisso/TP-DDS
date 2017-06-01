package model;
import java.util.ArrayList;
import java.util.List;
import parser.PARSER;
import parser.SCANNER;
import parser.TokenYTipo;


public class Indicador {
	String nombre;
	List<TokenYTipo> listaDeTokens; 
	
	@Override
	public String toString(){
		return nombre;
	}
	
	public Indicador(String expresion) { //Falta agregar la de cuentas
		SCANNER.analizarLinea(expresion);
    	List<TokenYTipo> lista = SCANNER.obtenerTokens();
    	
    	this.listaDeTokens =lista.subList(2, lista.size()-1);
    	this.nombre = lista.get(0).getValor();
    	
		if (listaDeTokens.stream().anyMatch(x->x.getValor().equals(nombre)) )
			throw new RuntimeException("Ingreso una definicion recursiva ") ;

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
		formula = String.join(" ", l );

		return nombre + " = " + formula;
	}
	
	

}
