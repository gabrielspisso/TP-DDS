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
	String opcion;
	int cantidadDeAños;
	Calculo calculo;
	int valorMinimo;
	criterioDeAceptacionDeCondicion comportamiento;
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
		return opcion.equals("Tipo 1");
	}
	public boolean isVisibleCalculo(){
		return opcion.equals("Tipo 3");
		
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
			case "Tipo 1":{
				condicion = new CondicionConAño(valorMinimo, cantidadDeAños, indicador,comportamiento);
			}
			case "Tipo 2":{
				condicion = new CondicionEntreDosEmpresas(indicador,comportamiento);
			}
			case "Tipo 3":{
					condicion = new condicionConCalculo(calculo,valorMinimo, indicador, comportamiento);
			}
			case "Tipo 4":{
				condicion = new CondicionConComportamiento(indicador, comportamiento);
			}
		
		}
		if(condicion != null){
			RepositorioDeCondiciones.agregarCondicion(Arrays.asList(condicion));
			
		}
		else{
			System.out.println("hola");
		}
	}
}
