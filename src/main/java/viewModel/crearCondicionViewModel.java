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
import condicionesYMetodologias.ValoresParaEvaluar;
import condicionesYMetodologias.condicionConCalculo;
import model.Indicador;
import opciones.Opcion;
import opciones.Opcion_1;
import opciones.Opcion_2;
import opciones.Opcion_3;
import opciones.Opcion_4;
import repositorios.RepositorioDeCondiciones;
import repositorios.RepositorioDeIndicadores;

@Observable
public class crearCondicionViewModel {
	String nombreDeIndicador;
	criterioDeAceptacionDeCondicion comportamiento;

	ValoresParaEvaluar valores  = new ValoresParaEvaluar();
	
	Indicador indicadorActual;
	//String opcion = "Default";
	int cantidadDeAños;
	int valorMinimo;
	Calculo calculo;
	
	public Indicador getIndicadorActual() {
		return valores.getIndicadorActual();
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
	public Opcion getOpcion() {
		return valores.getOpcion();
	}
	
	
	public List<Calculo> getCalculos(){
		return Arrays.asList(new Sumatoria(),new Mediana(),new Promedio());
	}
	
	
	public void setOpcion(Opcion opcion) {
//		this.opcion = opcion;
		valores.setOpcion(opcion);
		ObservableUtils.firePropertyChanged(this, "visibleCantidadDeAños");
		ObservableUtils.firePropertyChanged(this, "visibleCalculo");
		ObservableUtils.firePropertyChanged(this, "aclaracionTipo");
		ObservableUtils.firePropertyChanged(this, "visibleValorMinimo");
	}
	
	public String getAclaracionTipo(){
		
		return valores.getOpcion().getDescripcion();
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
	public List<Opcion> getOpciones(){
		return Arrays.asList(new Opcion_1(),new Opcion_2(),new Opcion_3(),new Opcion_4());
	}
	public boolean isVisibleCantidadDeAños(){
		return valores.getOpcion().isVisibleCantidadDeAños();
	}
	public boolean isVisibleCalculo(){
		return valores.getOpcion().isVisibleCalculo();
		
	}
	
	public boolean isVisibleValorMinimo(){
		  return valores.getOpcion().isVisibleValorMinimo();
	 }
	
	public void crearCondiciones(){
		
		if(comportamiento==null)
			throw new NoItemSelectedException();
		
		//Le falta terrible abstraccion para usar polimorfismo, pero no lo pienso hacer ahora
		
					
		if(valores.getOpcion() != null){
			Condicion condicion = valores.getOpcion().generarCondicion(indicadorActual, comportamiento, valorMinimo, cantidadDeAños, calculo);			
			RepositorioDeCondiciones.agregarCondicion(Arrays.asList(condicion));
		}

	}
}
