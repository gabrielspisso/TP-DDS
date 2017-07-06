package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Calculos.Calculo;
import Calculos.Mediana;
import Calculos.Promedio;
import Calculos.Sumatoria;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionTipo4;
import condicionesYMetodologias.condicionTipo1;
import condicionesYMetodologias.condicionTipo3;
import model.Indicador;
import repositorios.RepositorioDeCondiciones;
import repositorios.RepositorioDeIndicadores;

@Observable
public class crearCondicionViewModel {
	String nombreDeIndicador;
	String opcion;
	int cantidadDeAños;
	String calculo;
	int valorMinimo;
	boolean taxatividad;
	String comportamiento;
	public String getComportamiento() {
		return comportamiento;
	}
	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}
	public boolean isTaxatividad() {
		return taxatividad;
	}
	public void setTaxatividad(boolean taxatividad) {
		this.taxatividad = taxatividad;
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
	public void setOpcion(String opcion) {
		this.opcion = opcion;
		ObservableUtils.firePropertyChanged(this, "visibleCantidadDeAños");
		ObservableUtils.firePropertyChanged(this, "visibleCalculo");
		ObservableUtils.firePropertyChanged(this, "visibleComportamiento");
	}
	public int getCantidadDeAños() {
		return cantidadDeAños;
	}
	public void setCantidadDeAños(int cantidadDeAños) {
		this.cantidadDeAños = cantidadDeAños;
	}
	public String getCalculo() {
		return calculo;
	}
	public void setCalculo(String calculo) {
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
		return opcion.equals("Tipo 1");
	}
	public boolean isVisibleCalculo(){
		return opcion.equals("Tipo 3");
		
	}
	public boolean isVisibleComportamiento(){
		return opcion.equals("Tipo 4");
	}
	
	public void crearCondiciones(){
		Indicador indicador;
		try{
			 indicador = RepositorioDeIndicadores.getListaDeIndicadores().stream()
					.filter(ind -> ind.getNombre().equals(nombreDeIndicador))
					.findFirst().get();			
		}
		catch(RuntimeException ex){
			throw new RuntimeException("No existe el indicador para esta condicion");
		}
		Condicion condicion = null;
		switch(opcion){
			case "Opcion 1":{
				condicion = new condicionTipo1(valorMinimo, cantidadDeAños, indicador, taxatividad);
			}
			case "Opcion 2":{
				condicion = new condicionTipo1(valorMinimo, 1, indicador, taxatividad);
			}
			case "Opcion 3":{
				Calculo calculo = opcion=="Promedio"? new Promedio() : opcion == "Sumatoria" ? new Sumatoria() : new Mediana(); 
				condicion = new condicionTipo3(calculo,valorMinimo, indicador, taxatividad);
			}
			case "Opcion 4":{
				condicion = new CondicionTipo4(comportamiento, indicador, taxatividad);
			}
		
		}
		RepositorioDeCondiciones.agregarCondicion(Arrays.asList(condicion));
	}
}
