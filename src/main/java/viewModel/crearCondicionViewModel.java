package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Calculos.Calculo;
import Calculos.Mayor;
import Calculos.Mediana;
import Calculos.Menor;
import Calculos.Promedio;
import Calculos.Sumatoria;
import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConAño;
import condicionesYMetodologias.CondicionConComportamiento;
import condicionesYMetodologias.CondicionEntreDosEmpresas;
import condicionesYMetodologias.condicionConCalculo;
import model.Indicador;
import repositorios.RepositorioDeCondiciones;
import repositorios.RepositorioDeIndicadores;

@Observable
public class crearCondicionViewModel {
	String nombreDeIndicador;
	String opcion = "Tipo 5";
	int cantidadDeAños;
	Calculo calculo;
	int valorMinimo;
	criterioDeAceptacionDeCondicion comportamiento;
	
	Indicador indicadorActual;
	
	
	public Indicador getIndicadorActual() {
		return indicadorActual;
	}
	public void setIndicadorActual(Indicador indicadorActual) {
		this.indicadorActual = indicadorActual;
	}

	public List<Indicador> getIndicadores(){
		return RepositorioDeIndicadores.getListaDeIndicadores();
	}
		
	public criterioDeAceptacionDeCondicion getComportamiento() {
		return comportamiento;
	}
	public void setComportamiento(criterioDeAceptacionDeCondicion comportamiento) {
		this.comportamiento = comportamiento;
	}
	public String getNombreDeIndicador() {
		return nombreDeIndicador;
	}
	public void setNombreDeIndicador(String nombreDeIndicador) {
		this.nombreDeIndicador = nombreDeIndicador;
	}
	public String getOpcion() {
		return opcion;
	}
	
	
	public List<Calculo> getCalculos(){
		return Arrays.asList(new Sumatoria(),new Mediana(),new Promedio());
	}
	
	
	public void setOpcion(String opcion) {
		this.opcion = opcion;
		ObservableUtils.firePropertyChanged(this, "visibleCantidadDeAños");
		ObservableUtils.firePropertyChanged(this, "visibleCalculo");
		ObservableUtils.firePropertyChanged(this, "aclaracionTipo");
		ObservableUtils.firePropertyChanged(this, "visibleValorMinimo");
	}
	
	public String getAclaracionTipo(){
		
		if(opcion.equals("Tipo 1"))
			return "Que un indicador sea mayor o menor a cierto valor\n, en el último año o durante los últimos N años";
		if(opcion.equals("Tipo 2"))
			return "Que un indicador sea mayor o menor que el de otra empresa";
		if(opcion.equals("Tipo 3"))
			return "Que un promedio, mediana o sumatoria de un cierto indicador sea mayor o menor a cierto valor";
		if(opcion.equals("Tipo 4"))
			return "Que un indicador sea sea siempre o casi siempre creciente o decreciente durante un período";
		
		return "Descripcion";
	}
	
	
	public int getCantidadDeAños() {
		return cantidadDeAños;
	}

	public List<criterioDeAceptacionDeCondicion> getComportamientos(){
		return Arrays.asList(new Mayor(),new Menor());
	}
	
	public void setCantidadDeAños(int cantidadDeAños) {
		this.cantidadDeAños = cantidadDeAños;
	}
	
	public Calculo getCalculo() {
		return calculo;
	}
	
	public void setCalculo(Calculo calculo) {
		this.calculo = calculo;
	}
	
	public int getValorMinimo() {
		return valorMinimo;
	}
	
	public void setValorMinimo(int valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public List<String> getOpciones(){
		return Arrays.asList("Tipo 1", "Tipo 2", "Tipo 3", "Tipo 4");
	}
	public boolean isVisibleCantidadDeAños(){
		return opcion.equals("Tipo 1")||opcion.equals("Tipo 4");
	}
	public boolean isVisibleCalculo(){
		return opcion.equals("Tipo 3");
		
	}
	
	public boolean isVisibleValorMinimo(){
		  return opcion.equals("Tipo 1")|| opcion.equals("Tipo 3");
		  
		 }
	
	public void crearCondiciones(){
		
		if(comportamiento==null)
			throw new NoItemSelectedException();
		
		Condicion condicion = null;
	
		//Le falta terrible abstraccion para usar polimorfismo, pero no lo pienso hacer ahora
			if(opcion.equals( "Tipo 1"))
				condicion = new CondicionConAño(valorMinimo, cantidadDeAños, indicadorActual, comportamiento);
			if(opcion.equals("Tipo 2"))
				condicion = new CondicionEntreDosEmpresas(indicadorActual,comportamiento);
			if(opcion.equals("Tipo 3"))
					condicion = new condicionConCalculo(calculo,valorMinimo, indicadorActual, comportamiento);
			if(opcion.equals("Tipo 4"))
				condicion = new CondicionConComportamiento(indicadorActual, comportamiento, cantidadDeAños);
					
		if(condicion != null)
			RepositorioDeCondiciones.agregarCondicion(Arrays.asList(condicion));

	}
}
