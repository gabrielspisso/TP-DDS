package model.condicionesYMetodologias;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.ibm.icu.util.IndianCalendar;

import model.Balance;
import model.Empresa;
import model.Indicador;
import model.Calculos.Calculo;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;
import model.repositorios.RepositorioDeIndicadores;

@Entity
public class condicionConCalculo extends Condicion {

	double valorMinimo;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Calculo calculo;
	
	private condicionConCalculo() {
		super();
	}
	
	public condicionConCalculo(Indicador indicador, criterioDeAceptacionDeCondicion criterio, Calculo calculo, double valorMinimo,String nombre){
		super(indicador,criterio,nombre);
		this.valorMinimo = valorMinimo;
		this.calculo = calculo;	
	}
	
	public boolean cumpleLaCondicion(Empresa empresa,  Empresa empresaNULL) {
		Stream<Double>  StreamDeValores= empresa.getBalances().stream().map(balance->indicador.calcularValor(balance.getCuentas()));
		
		double resultado =calculo.realizarCalculo(StreamDeValores.collect(Collectors.toList()));
		return criterio.cumpleCriterioDeAceptacionDeCondicion(valorMinimo, resultado);			
	}
	
	//@Override
	public boolean seCumpleCondicionFiltrar(Empresa empresa){
		Stream<Double>  StreamDeValores= empresa.getBalances().stream().map(balance->indicador.calcularValor(balance.getCuentas()));
	
			double resultado =calculo.realizarCalculo(StreamDeValores.collect(Collectors.toList()));
			return criterio.cumpleCriterioDeAceptacionDeCondicion(valorMinimo, resultado);			
		
	}
}
