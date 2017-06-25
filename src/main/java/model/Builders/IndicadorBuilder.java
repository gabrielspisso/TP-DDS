package model.Builders;

import java.util.List;

import model.Indicador;
import model.Termino;
import parser.SCANNER;
import parser.TokenYTipo;

public class IndicadorBuilder {

	
	public static Indicador Build(String expresion) {
    	List<TokenYTipo> lista = SCANNER.obtenerTokens(expresion);
    	String nombre = lista.get(0).getValor();
    	List<TokenYTipo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	List<Termino> listaDeTerminos = ArbolBuilder.Build(listaDeTokens);
    	
		return new Indicador(nombre, listaDeTerminos);
	}
}
