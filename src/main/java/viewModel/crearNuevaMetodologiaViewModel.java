package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import condicionesYMetodologias.Condicion;
import repositorios.RepositorioDeCondiciones;

@Observable
public class crearNuevaMetodologiaViewModel {

		private String nombreMetodologia="Ingrese nombre";
		private List<Condicion> condicionesRestantes = RepositorioDeCondiciones.mostrarListaDeCondiciones();
		private List<Condicion> condicionesSeleccionadas = new ArrayList<Condicion>();
		private Condicion condicionActualAAgregar;
		private Condicion condicionActualAQuitar;
	
		

		public List<Condicion> getCondiciones(){
			return RepositorioDeCondiciones.mostrarListaDeCondiciones();
		}
		public Condicion getCondicionActualAAgregar() {
			return condicionActualAAgregar;
		}

		public void setCondicionActualAAgregar(Condicion condicionActualAAgregar) {
			this.condicionActualAAgregar = condicionActualAAgregar;
		}

		public Condicion getCondicionActualAQuitar() {
			return condicionActualAQuitar;
		}

		public void setCondicionActualAQuitar(Condicion condicionActualAQuitar) {
			this.condicionActualAQuitar = condicionActualAQuitar;
		}

		public List<Condicion> getCondicionesRestantes() {
			return condicionesRestantes;
		}

		public List<Condicion> getCondicionesSeleccionadas() {
			return condicionesSeleccionadas;
		}

		public String getNombreMetodologia() {
			return nombreMetodologia;
		}

		public void setNombreMetodologia(String nombreMetodologia) {
			this.nombreMetodologia = nombreMetodologia;
		}
		public void crearMetodologia(){
			
		}
		public void quitarCondicion(){
			condicionesSeleccionadas.removeIf(condicion1 -> condicion1.equals(condicionActualAQuitar));
			condicionesRestantes.add(condicionActualAQuitar);
			ObservableUtils.firePropertyChanged(this, "condicionesRestantes");
			ObservableUtils.firePropertyChanged(this, "condicionesSeleccionadas");
		}
		public void agregarCondicion(){
			condicionesSeleccionadas.add(condicionActualAAgregar);
			ObservableUtils.firePropertyChanged(this, "condicionesRestantes");
			ObservableUtils.firePropertyChanged(this, "condicionesSeleccionadas");
		}

}
