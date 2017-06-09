package parser;

import java.util.ArrayList;
import java.util.List;

import model.Indicador;

public class IndicadorBuilder {
	
	private static List<Termino> terminos;
	private static Termino terminoActual = null;
	
	public static Indicador Build(String expresion) {
    	List<TokenYTipo> lista = SCANNER.obtenerTokens(expresion);
    	String nombre = lista.get(0).getValor();
    	List<TokenYTipo> listaDeTokens =lista.subList(2, lista.size()-1);
    	
    	terminos = new ArrayList<>();
    	terminoActual = new Termino(1, new ArrayList<>(), new ArrayList<>());
    	terminos.add(terminoActual);
    	
    	listaDeTokens.stream().forEach(token -> clasificarToken(token));
    	
    	
    	
		return new Indicador(nombre, terminos);
	}
	
	private static void clasificarToken(TokenYTipo token) {
		enNumero enumval = enNumero.valueOf(token.getTipo());
		switch(enumval){
		case Identificador:{
			terminoActual
				.getListaDeValores()
				.add(new IndicadorOCuenta(token)
				);
		}
			
		case OperadorPrimario:{
			int signo = (token.getValor().equals("+")) ? 1 : -1; //El signo es 1 o -1
			terminoActual = new Termino(signo, new ArrayList<>(), new ArrayList<>());
			terminos.add(terminoActual);
		}
		case OperadorSecundario:{
			terminoActual
				.getListaDeOperadores()
				.add(new operadorSecundario(token)
				);
		}
			
		case NUMERO:{
			terminoActual
				.getListaDeValores()
				.add(new Numero(token)
			);
		}
		case FinDeLinea:
		default:
		}
	}
	
	
	private enum enNumero{
		Identificador,
		NUMERO,
		OperadorPrimario,
		OperadorSecundario,
		FinDeLinea
	}
}
