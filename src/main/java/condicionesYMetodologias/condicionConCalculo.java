package condicionesYMetodologias;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ibm.icu.util.IndianCalendar;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class condicionConCalculo extends Condicion {

	int valorMinimo;
	Calculo calculo;
	public condicionConCalculo(Calculo calculo, int valorMinimo, Indicador indicador,criterioDeAceptacionDeCondicion criterio){
		super(indicador,criterio);
		this.valorMinimo = valorMinimo;
		this.calculo = calculo;	
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		Stream<Double>  StreamDeValores= empresa.getBalances().stream().map(balance->indicador.calcularValor(balance.getCuentas()));
		double resultado =calculo.realizarCalculo(StreamDeValores.collect(Collectors.toList()));
		return criterio.cumpleCriterioDeAceptacionDeCondicion(valorMinimo, resultado);
	}
}
