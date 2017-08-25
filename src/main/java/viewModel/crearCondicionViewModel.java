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


	ValoresParaEvaluar valores  = new ValoresParaEvaluar();

	
	public Indicador getIndicadorActual() {
		return valores.getIndicadorActual();
	}
	public void setIndicadorActual(Indicador indicadorActual) {
		this.valores.setIndicadorActual( indicadorActual);
	}

	public List<Indicador> getIndicadores(){
		return RepositorioDeIndicadores.getListaDeIndicadores();
	}
		
	public criterioDeAceptacionDeCondicion getComportamiento() {
		return valores.getComportamiento();
	}
	public void setComportamiento(criterioDeAceptacionDeCondicion comportamiento) {
		valores.setComportamiento(comportamiento);
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
	
	
	public void setNombre(String nombre){
		valores.setNombre(nombre);
	}
	public String getNombre(){
		return valores.getNombre();
	}
	public String getAclaracionTipo(){
		
		return valores.getOpcion().getDescripcion();
	}
	
	
	public int getCantidadDeAños() {
		return valores.getCantidadDeAños();
	}

	public List<criterioDeAceptacionDeCondicion> getComportamientos(){
		return Arrays.asList(new Mayor(),new Menor());
	}
	
	public void setCantidadDeAños(int cantidadDeAños) {
		this.valores.setCantidadDeAños( cantidadDeAños);
	}
	
	public Calculo getCalculo() {
		return this.valores.getCalculo();
	}
	
	public void setCalculo(Calculo calculo) {
		this.valores.setCalculo(calculo);
	}
	
	public int getValorMinimo() {
		return valores.getValorMinimo();
	}
	
	public void setValorMinimo(int valorMinimo) {
		valores.setValorMinimo(valorMinimo);
	}
	public List<Opcion> getOpciones(){
		return Arrays.asList(new Opcion_1(),new Opcion_2(),new Opcion_3(),new Opcion_4());
	}
	public boolean isVisibleCantidadDeAños(){
		System.out.println(valores.getOpcion().toString());
		return valores.getOpcion().isVisibleCantidadDeAños() &&valores.getOpcion()!=null;
	}
	public boolean isVisibleCalculo(){
		return valores.getOpcion().isVisibleCalculo();
		
	}
	
	public boolean isVisibleValorMinimo(){
		  return valores.getOpcion().isVisibleValorMinimo();
	 }
	
	public void crearCondiciones(){
		
		if(valores.getComportamiento()==null || valores.getNombre() == null || valores.getIndicadorActual() == null)
			throw new NoItemSelectedException();
		
		//Le falta terrible abstraccion para usar polimorfismo, pero no lo pienso hacer ahora
		
					
		if(valores.getOpcion() != null){
			Condicion condicion = valores.getOpcion().generarCondicion(valores);			
			RepositorioDeCondiciones.agregarCondicion(Arrays.asList(condicion));
		}
		else{
			System.out.println("error");
		}
	}
}
