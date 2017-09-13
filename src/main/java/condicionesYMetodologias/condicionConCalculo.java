package condicionesYMetodologias;

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

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

@Entity
public class condicionConCalculo extends Condicion {

	double valorMinimo;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Calculo calculo;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Indicador indicador;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private criterioDeAceptacionDeCondicion criterio;
	
	public condicionConCalculo(Indicador indicador, criterioDeAceptacionDeCondicion criterio, Calculo calculo, double valorMinimo,String nombre){
		super(nombre);
		this.indicador = indicador;
		this.criterio = criterio; 
		this.valorMinimo = valorMinimo;
		this.calculo = calculo;	
	}
	public condicionConCalculo(ValoresParaEvaluar valores) {
		// TODO Auto-generated constructor stub
		super(valores.getNombre());
		this.indicador = valores.getIndicadorActual();
		this.criterio = valores.getComportamiento(); 
	
		this.valorMinimo = valores.getValorMinimo();
		this.calculo = valores.getCalculo();	
	}
	@Override
	public boolean seCumpleLaCondicion(Empresa empresa,Empresa empresa1){
		Stream<Double>  StreamDeValores= empresa.getBalances().stream().map(balance->indicador.calcularValor(balance.getCuentas()));
	
			double resultado =calculo.realizarCalculo(StreamDeValores.collect(Collectors.toList()));
			return criterio.cumpleCriterioDeAceptacionDeCondicion(valorMinimo, resultado);			
		
	}
	/*
	public String toString(){
		return "el " + calculo.toString()+ "del indicador"+ indicador.toString() +"es "+ criterio.toString() + " a "+ valorMinimo;
		
		//Intente que no repita lo de indicador to string y que se ocupe la clase padre, pero a java no le importo y me tomaba la del padre.
	}
	*/
}
