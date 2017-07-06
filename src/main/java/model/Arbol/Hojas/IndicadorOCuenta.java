package model.Arbol.Hojas;

import java.util.List;
import java.util.NoSuchElementException;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Operacion;
import repositorios.RepositorioDeIndicadores;

public class IndicadorOCuenta extends Hoja{
	private String nombreDelIndicadorFinal;
	public IndicadorOCuenta(String valorDeHoja) {
		super(valorDeHoja);
		
	}


	@Override
	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarValorDeIdentificador(listaDeCuentas, listaDeIndicadores);
		}
		catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}

	private double buscarValorDeIdentificador(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		try{
			return buscarEnCuentas(listaDeCuentas);
		}
		catch(NoSuchElementException ex){
			return buscarEnIndicadores(listaDeIndicadores, listaDeCuentas);
		}
	}
	
	private double buscarEnCuentas( List<Cuenta> listaDeCuentas){
		Cuenta cuenta;
		cuenta = listaDeCuentas.stream()
				.filter(cuent -> cuent.getNombre().equals(valorDeHoja))
				.findFirst().get();
		return cuenta.getValor();
	}

	private double buscarEnIndicadores(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		try{

			indicador = listaDeIndicadores.stream()
					.filter(indic -> indic.getNombre().equals(valorDeHoja))
					.findFirst().get();
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("El valor "
					+"\"" +  valorDeHoja
					+"\" no se encontro en el listado de cuentas ni de indicadores");
		}
			
		return indicador.calcularValor(listaDeCuentas);
	}
	public boolean noEsRecursivo(String token){
		Indicador indicador;
		try{

			indicador = RepositorioDeIndicadores.getListaDeIndicadores().stream()
					.filter(indic -> indic.getNombre().equals(valorDeHoja))
					.findFirst().get();
			return indicador.contieneEsteToken(token);
		}
		catch(NoSuchElementException e){
			return false;
		}
	}
	@Override
	public boolean contieneEsteToken(String string) {
		return valorDeHoja.equals(string) || noEsRecursivo(string);
	}

}
