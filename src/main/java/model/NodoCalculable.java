package model;

import java.util.List;
import java.util.NoSuchElementException;

public class NodoCalculable implements Nodo{

	private String valor;
	private boolean esUnNumero;
	
	public NodoCalculable(String unToken, boolean esUnNumero) {
		this.valor = unToken;
		this.esUnNumero = esUnNumero;
	}
	
	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		if(esUnNumero)
			return Double.parseDouble(valor);
		try{
			return buscarValorDeIdentificador(listaDeCuentas, listaDeIndicadores);
		}
		catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public String mostrarFormula() {
		return valor;
	}

	private double buscarValorDeIdentificador(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarEnCuentas(listaDeIndicadores, listaDeCuentas);
		}
		catch(NoSuchElementException ex){
			return buscarEnIndicadores(listaDeIndicadores, listaDeCuentas);
		}
	}
	
	private double buscarEnCuentas(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Cuenta cuenta;
		cuenta = listaDeCuentas.stream()
				.filter(cuent -> cuent.getNombre().equals(valor))
				.findFirst().get();

		return cuenta.getValor();
	}
	
	private double buscarEnIndicadores(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		try{
			if(listaDeIndicadores.stream().anyMatch(indic -> indic.contieneEsteToken(valor)))
				throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			indicador = listaDeIndicadores.stream()
					.filter(indic -> indic.getNombre().equals(valor))
					.findFirst().get();
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("El valor "
					+"\"" +  valor
					+"\" no se encontro en el listado de cuentas ni de indicadores");
		}
			
		return indicador.calcularValor(listaDeCuentas, listaDeIndicadores);
	}

	@Override
	public boolean contieneEsteToken(String string) {
		return valor.equals(string);
	}
}
