package viewModel;

import org.uqbar.commons.utils.Observable;

import model.CargadorDeEmpresas;
import repositorios.RepositorioDeEmpresas;
@Observable
public class cargarEmpresasViewModel {
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
