package viewModel;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.CargadorDeEmpresas;
import model.Empresa;
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
		List<Empresa> le = CargadorDeEmpresas.obtenerCuentasEmpresas(rutaArchivo);
		le.forEach(e -> RepositorioDeEmpresas.agregarEmpresas(e));
		bloq = true;
	}
}
