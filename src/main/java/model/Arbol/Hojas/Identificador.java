package model.Arbol.Hojas;

import java.util.List;
import java.util.NoSuchElementException;

import Excepciones.IdentificadorInexistente;
import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.NODO;
import repositorios.RepositorioDeIndicadores;

public class Identificador extends Hoja{
	private String nombreDelIndicadorFinal;
	public Identificador(String valor) {
		super(valor);
		
	}


	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		//try{
			return buscarValorDeIdentificador(listaDeCuentas, listaDeIndicadores);
		//}
		/*catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}*/
	}
	private boolean estaEnCuentas(List<Cuenta> listaDeCuentas){
		return  listaDeCuentas.stream()
				.anyMatch(cuent -> cuent.getNombre().equals(valor));
	}
	private boolean estaEnIndicadores(List<Indicador> listaDeIndicadores){
		return listaDeIndicadores.stream()
				.anyMatch(indic -> indic.getNombre().equals(valor));
	}
	private double buscarValorDeIdentificador(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		if(estaEnCuentas(listaDeCuentas)){
			return buscarEnCuentas(listaDeCuentas);
		}
		else if(estaEnIndicadores(listaDeIndicadores)){
			return buscarEnIndicadores(listaDeIndicadores, listaDeCuentas);
		}
		throw new IdentificadorInexistente("El valor "
				+"\"" +  valor
				+"\" no se encontro en el listado de cuentas ni de indicadores");
	}
	
	private double buscarEnCuentas( List<Cuenta> listaDeCuentas){
		Cuenta cuenta;
		cuenta = listaDeCuentas.stream()
				.filter(cuent -> cuent.getNombre().equals(valor))
				.findFirst().get();
		return cuenta.getValor();
	}

	private double buscarEnIndicadores(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;


			indicador = listaDeIndicadores.stream()
					.filter(indic -> indic.getNombre().equals(valor))
					.findFirst().get();
			
		return indicador.calcularValor(listaDeCuentas);
	}
	public boolean noEsRecursivo(String token){
		Indicador indicador;
		if(!estaEnIndicadores(RepositorioDeIndicadores.getListaDeIndicadores())){
			return false;
		}
		indicador = RepositorioDeIndicadores.getListaDeIndicadores().stream()
					.filter(indic -> indic.getNombre().equals(valor))
					.findFirst().get();
			return indicador.contieneEsteToken(token);
		
	}
	@Override
	public boolean contieneEsteToken(String string) {
		return valor.equals(string) || noEsRecursivo(string);
	}

}
