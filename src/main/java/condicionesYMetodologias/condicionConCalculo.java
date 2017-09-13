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
public class condicionConCalculo extends CondicionUnitaria {

	double valorMinimo;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Calculo calculo;
	
	
	public condicionConCalculo(Indicador indicador, criterioDeAceptacionDeCondicion criterio, Calculo calculo, double valorMinimo,String nombre){
		super(indicador,criterio,nombre);

		this.valorMinimo = valorMinimo;
		this.calculo = calculo;	
	}
	public condicionConCalculo(ValoresParaEvaluar valores) {
		// TODO Auto-generated constructor stub
		super(valores);
	
		this.valorMinimo = valores.getValorMinimo();
		this.calculo = valores.getCalculo();	
	}
	@Override
	public boolean seCumpleLaCondicionUnitaria(Empresa empresa,Empresa empresa1){
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
