package viewModel;
import repositorios.RepositorioDeIndicadores;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;


import model.IOs;
import model.Indicador;
import parser.IndicadorBuilder;

@Observable
public class crearIndicadoresViewModel {
	
	private String indicadorActual="";
	private boolean guardarEnArchivo = false;
	
	public String getIndicadorActual() {
		return indicadorActual;
	}


	public void setIndicadorActual(String indicadorActual) {
		this.indicadorActual = indicadorActual;
		ObservableUtils.firePropertyChanged(this, "noEstaVacio");
	}
	
	public void crearIndicador(){	
			Indicador nuevoIndicador = IndicadorBuilder.Build(indicadorActual + ";");
			RepositorioDeIndicadores.agregarIndicador(nuevoIndicador);
			if(guardarEnArchivo) 
				IOs.cargarIndicador(nuevoIndicador, "archivoIndicadores.txt");
			
			indicadorActual="";
	}
	public boolean isNoEstaVacio(){
		return !indicadorActual.isEmpty();
	}


	public boolean isGuardarEnArchivo() {
		return guardarEnArchivo;
	}


	public void setGuardarEnArchivo(boolean guardarEnArchivo) {
		this.guardarEnArchivo = guardarEnArchivo;
	}
	

}
