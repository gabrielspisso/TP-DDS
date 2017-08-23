package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import model.Empresa;
import repositorios.RepositorioDeEmpresas;
@Observable
public class evaluarEmpresasViewModel {
	
	private Empresa empresaAAgregar ;
	private Empresa empresaAQuitar ;
	private List<Empresa> empresasSeleccionadas = new ArrayList<Empresa>();
	private List<Empresa> empresasRestantes = RepositorioDeEmpresas.mostrarEmpresas();
	
	
	public void quitarEmpresa(){
		if(empresaAQuitar != null){
			empresasSeleccionadas.removeIf(condicion1 -> condicion1.equals(empresaAQuitar));
			empresasRestantes.add(empresaAQuitar);
			ObservableUtils.firePropertyChanged(this, "empresasRestantes");
			ObservableUtils.firePropertyChanged(this, "empresasSeleccionadas");
		}
		else{
			throw new NoItemSelectedException();
		}
	}
	public Empresa getEmpresaAAgregar() {
		return empresaAAgregar;
	}

	public void setEmpresaAAgregar(Empresa empresaAAgregar) {
		this.empresaAAgregar = empresaAAgregar;
	}

	public Empresa getEmpresaAQuitar() {
		return empresaAQuitar;
	}

	public void setEmpresaAQuitar(Empresa empresaAQuitar) {
		this.empresaAQuitar = empresaAQuitar;
	}

	public List<Empresa> getEmpresasSeleccionadas() {
		return empresasSeleccionadas;
	}

	public void setEmpresasSeleccionadas(List<Empresa> empresasSeleccionadas) {
		this.empresasSeleccionadas = empresasSeleccionadas;
	}

	public List<Empresa> getEmpresasRestantes() {
		return empresasRestantes;
	}

	public void setEmpresasRestantes(List<Empresa> empresasRestantes) {
		this.empresasRestantes = empresasRestantes;
	}

	public void seleccionarEmpresa(){
		if(empresaAAgregar != null){
			empresasRestantes.removeIf(condicion1 -> condicion1.equals(empresaAAgregar));
			empresasSeleccionadas.add(empresaAAgregar);
		}
		else{
			throw new NoItemSelectedException();
		}
	}
	
}
