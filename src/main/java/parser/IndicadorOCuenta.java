package parser;

import java.util.List;
import java.util.NoSuchElementException;

import model.Cuenta;
import model.Indicador;

public class IndicadorOCuenta implements etiquetaCalculable{

	private TokenYTipo token;
	
	public IndicadorOCuenta(TokenYTipo unToken) {
		this.token = unToken;
	}
	
	@Override
	public double obtenerValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarValorDeIdentificador(listaDeCuentas, listaDeIndicadores);
		}
		catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public String obtenerEtiqueta() {
		return token.getValor();
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
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();

		return cuenta.getValor();
	}
	
	private double buscarEnIndicadores(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		
		try{
			indicador = listaDeIndicadores.stream()
				.filter(x -> x.getNombre().equals(token.getValor()))
				.findFirst().get();
			if(indicador.contieneEsteToken(token))
				throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("El valor "
					+"\""
					+token.getValor() 
					+"\" no se encontro en el listado de cuentas ni de indicadores");
		}
			
		return indicador.calcularValor(listaDeCuentas, listaDeIndicadores);
	}
}
