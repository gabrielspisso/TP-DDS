package condicionesYMetodologias;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ibm.icu.util.IndianCalendar;

import Calculos.Calculo;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class condicionTipo3 extends Condicion {

	int valorMinimo;
	Calculo calculo;
	public condicionTipo3(Calculo calculo, int valorMinimo, Indicador indicador){
		super(indicador);
		this.valorMinimo = valorMinimo;
		this.calculo = calculo;	
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa){
		Stream<Double>  x= empresa.getBalances().stream().map(balance->indicador.calcularValor(balance.getCuentas()));
		return calculo.realizarCalculo(x.collect(Collectors.toList())) > valorMinimo;
	}
}
