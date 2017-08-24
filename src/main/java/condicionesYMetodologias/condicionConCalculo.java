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

	double valorMinimo;
	Calculo calculo;
	public condicionConCalculo(Indicador indicador, criterioDeAceptacionDeCondicion criterio, Calculo calculo, double valorMinimo){
		super(indicador,criterio);
		this.valorMinimo = valorMinimo;
		this.calculo = calculo;	
	}
	@Override
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa1){
		Stream<Double>  StreamDeValores= empresa.getBalances().stream().map(balance->indicador.calcularValor(balance.getCuentas()));
		double resultado =calculo.realizarCalculo(StreamDeValores.collect(Collectors.toList()));
		return criterio.cumpleCriterioDeAceptacionDeCondicion(valorMinimo, resultado);
	}
	public String toString(){
		return "el " + calculo.toString()+ "del indicador"+ indicador.toString() +"es "+ criterio.toString() + " a "+ valorMinimo;
		
		//Intente que no repita lo de indicador to string y que se ocupe la clase padre, pero a java no le importo y me tomaba la del padre.
	}
}
