package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.Metodologia;
import model.CargadorDeEmpresas;
import model.Empresa;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeMetodologias;
import scala.util.control.Exception;



@Observable
public class importarExportarArchivosViewModel {
	
	private String nombre_ArchivoEmpresa = "archivo-Empresa";
	private String rutaArchivo = "archivoEmpresas.txt";
	private String nombre_ArchivoMetodologia = "archivo-Metodologias";
	private String rutaArchivoMetodologias ="rutaArchivoMetodologias.txt";
	

	public String getNombre_ArchivoIndicadores() {
		return nombre_ArchivoIndicadores;
	}

	public void setNombre_ArchivoIndicadores(String nombre_ArchivoIndicadores) {
		this.nombre_ArchivoIndicadores = nombre_ArchivoIndicadores;
	}

	public String getRutaArchivoIndicadores() {
		return rutaArchivoIndicadores;
	}

	public void setRutaArchivoIndicadores(String rutaArchivoIndicadores) {
		this.rutaArchivoIndicadores = rutaArchivoIndicadores;
	}

	private String nombre_ArchivoIndicadores = "archivo-Indicadores";
	private String rutaArchivoIndicadores ="rutaArchivoIndicadores.txt";
	
	
	public String getRutaArchivoMetodologias() {
		return rutaArchivoMetodologias;
	}

	public void setRutaArchivoMetodologias(String rutaArchivoMetodologias) {
		this.rutaArchivoMetodologias = rutaArchivoMetodologias;
	}

	public String getNombre_ArchivoMetodologia() {
		return nombre_ArchivoMetodologia;
	}

	public void setNombre_ArchivoMetodologia(String nombre_ArchivoMetodologia) {
		this.nombre_ArchivoMetodologia = nombre_ArchivoMetodologia;
	}

	public void setNombre_ArchivoEmpresa(String nombre){
		this.nombre_ArchivoEmpresa = nombre;
	}
	
	public String getNombre_ArchivoEmpresa() {
		return nombre_ArchivoEmpresa;
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
	}
	
	public void cargarEmpresasDesdeLaDB() {
		RepositorioDeEmpresas.traerEmpresasDeLaDB();
	}
	
	
	
}
