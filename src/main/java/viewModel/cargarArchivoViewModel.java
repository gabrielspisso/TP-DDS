package viewModel;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.CargadorDeEmpresas;
import repositorios.RepositorioDeEmpresas;
@Observable
public class cargarArchivoViewModel {
	private String rutaArchivo;
	
	public void setRutaArchivo(String rutaArchivo){
		this.rutaArchivo = rutaArchivo;
	}
	
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void cargarEmpresas(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas(rutaArchivo));
	}
}
