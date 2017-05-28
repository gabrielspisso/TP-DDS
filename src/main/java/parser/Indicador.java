package parser;
import java.util.ArrayList;
import java.util.List;

import model.Cuenta;

public class Indicador {
	String nombre;
	int valor;
	List<Cuenta> listaDeCuentas;
	List<Indicador> listaDeIndicadores;
	List<TipoDeToken> listaDeTokens = new ArrayList<>();
	
	public Indicador(List<Indicador> listaDeIndicadores, StringBuilder nombre) { //Falta agregar la de cuentas
		this.listaDeIndicadores = listaDeIndicadores;
		this.nombre = nombre.toString();
	}
	
	public void recibirToken(TipoDeToken token){
		listaDeTokens.add(token);
	}
	
	private void arreglarNombre(StringBuilder nombre){
		String aux = nombre.substring(nombre.toString().length()-1);
		//nombre = aux;
	}
}
