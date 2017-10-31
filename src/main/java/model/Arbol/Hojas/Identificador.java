package model.Arbol.Hojas;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.Entity;

import model.Cuenta;
import model.Indicador;
import model.Usuario;
import model.Arbol.Operaciones.Nodo;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadores;
@Entity
public class Identificador extends Operando{
	protected Identificador() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Identificador(String valor) {
		super(valor);
		
	}


	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
			return buscarValorDeIdentificador(listaDeCuentas, listaDeIndicadores);
	}
	private boolean estaEnCuentas(List<Cuenta> listaDeCuentas){
		return  listaDeCuentas.stream()
				.anyMatch(cuent -> cuent.getNombre().equals(valor));
	}
	private boolean estaEnIndicadores(List<Indicador> listaDeIndicadores){
			
		return listaDeIndicadores.stream().anyMatch(indic -> indic.getNombre().equals(valor));
	}
	
	private double buscarValorDeIdentificador(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		if(estaEnCuentas(listaDeCuentas)){
			return buscarEnCuentas(listaDeCuentas);
		}
		else if(estaEnIndicadores(listaDeIndicadores)){
			return buscarEnIndicadores(listaDeIndicadores, listaDeCuentas);
		}
		throw new IdentificadorInexistente(valor);
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
	public boolean noEsRecursivo(String token, Long id){
		Indicador indicador;
		if(!estaEnIndicadores(RepositorioDeIndicadores.traerIndicadoresDeLaDB(id))){
			return false;
		}
		indicador = RepositorioDeIndicadores.traerIndicadoresDeLaDB(id).stream()
					.filter(indic -> indic.getNombre().equals(valor))
					.findFirst().get();
			return indicador.contieneEsteToken(token);
		
	}
	@Override
	public boolean contieneEsteToken(String string, Long id) {
		return valor.equals(string) || noEsRecursivo(string, id);
	}


}
