package viewModel;

import org.uqbar.commons.utils.Observable;

import model.CargadorDeEmpresas;
import repositorios.RepositorioDeEmpresas;
@Observable
public class menuPrincipalViewModel {
	private String rutaArchivo = "archivoEmpresas.txt";
	private boolean bloq = false;

	
	public boolean isBloq() {
		return bloq;
	}

	public void setRutaArchivo(String rutaArchivo){
		this.rutaArchivo = rutaArchivo;
	}
	
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void cargarEmpresas(){
		RepositorioDeEmpresas.agregarEmpresas(CargadorDeEmpresas.obtenerCuentasEmpresas(rutaArchivo));
		bloq=true;
	}
}
