package model.Arbol.Hojas;

import java.util.List;
import java.util.NoSuchElementException;

import model.Cuenta;
import model.Indicador;
import model.Arbol.Operaciones.Operacion;

public class IndicadorOCuenta extends Hoja{
	
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
			return buscarEnCuentas(listaDeIndicadores, listaDeCuentas);
		}
		catch(NoSuchElementException ex){
			return buscarEnIndicadores(listaDeIndicadores, listaDeCuentas);
		}
	}
	
	private double buscarEnCuentas(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Cuenta cuenta;
		cuenta = listaDeCuentas.stream()
				.filter(cuent -> cuent.getNombre().equals(valorDeHoja))
				.findFirst().get();

		return cuenta.getValor();
	}
	
	private double buscarEnIndicadores(List<Indicador> listaDeIndicadores, List<Cuenta> listaDeCuentas){
		Indicador indicador;
		try{
			if(listaDeIndicadores.stream().anyMatch(indic -> indic.contieneEsteToken(valorDeHoja)))
				throw new RuntimeException("Es recursivo, modifique uno de los dos para calcular el valor nuevamente");
			indicador = listaDeIndicadores.stream()
					.filter(indic -> indic.getNombre().equals(valorDeHoja))
					.findFirst().get();
		}
		catch(NoSuchElementException e){
			throw new RuntimeException("El valor "
					+"\"" +  valorDeHoja
					+"\" no se encontro en el listado de cuentas ni de indicadores");
		}
			
		return indicador.calcularValor(listaDeCuentas, listaDeIndicadores);
	}

	@Override
	public boolean contieneEsteToken(String string) {
		return valorDeHoja.equals(string);
	}

}
