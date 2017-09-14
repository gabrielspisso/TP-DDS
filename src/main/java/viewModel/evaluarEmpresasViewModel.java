package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.Metodologia;
import model.Empresa;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeMetodologias;
import scala.util.control.Exception;
@Observable
public class evaluarEmpresasViewModel {
	
	private Empresa empresaAAgregar ;
	private Empresa empresaAQuitar ;
	private List<Empresa> empresasSeleccionadas = new ArrayList<Empresa>();
	private List<Empresa> empresasRestantes = RepositorioDeEmpresas.traerEmpresasDeLaDB();
	private List<Empresa> empresasOrdenadas  = new ArrayList<Empresa>();
	private Metodologia metodologia;
	
	public void quitarEmpresa(){
		if(empresaAQuitar != null){
			empresasSeleccionadas.removeIf(condicion1 -> empresaAQuitar.equals(condicion1));
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
			empresasSeleccionadas.removeIf(condicion1 -> condicion1.equals(empresaAAgregar));

			empresasSeleccionadas.add(empresaAAgregar);
			ObservableUtils.firePropertyChanged(this, "empresasRestantes");
			ObservableUtils.firePropertyChanged(this, "empresasSeleccionadas");
		}
		else{
			throw new NoItemSelectedException();
		}
	}
	
	public Metodologia getMetodologia() {
		return metodologia;
	}
	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}
	public List<Metodologia> getMetodologias(){
		return RepositorioDeMetodologias.traerMetodologiasDeLaDB();
	}
	
	
	
	public void evaluarMetodologia()
	{
		if(metodologia==null)
				throw new NoItemSelectedException();
		empresasOrdenadas = metodologia.listarEmpresas(empresasSeleccionadas);
		ObservableUtils.firePropertyChanged(this,"empresasOrdenadas");

	}
	public List<Empresa> getEmpresasOrdenadas() {
		return empresasOrdenadas;
	}
	public void setEmpresasOrdenadas(List<Empresa> empresasOrdenadas) {
		this.empresasOrdenadas = empresasOrdenadas;
	}
	
	
	
}
