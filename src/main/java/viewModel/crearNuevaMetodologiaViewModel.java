package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.Metodologia;
import repositorios.RepositorioDeCondiciones;
import repositorios.RepositorioDeMetodologias;

@Observable
public class crearNuevaMetodologiaViewModel {

		private String nombreMetodologia="Ingrese nombre";
		private List<Condicion> condicionesRestantes = RepositorioDeCondiciones.mostrarListaDeCondiciones();
		private List<Condicion> condicionesSeleccionadas = new ArrayList<Condicion>();
		private Condicion condicionActualAAgregar;
		public void setCondicionesRestantes(List<Condicion> condicionesRestantes) {
			this.condicionesRestantes = condicionesRestantes;
		}
		public void setCondicionesSeleccionadas(List<Condicion> condicionesSeleccionadas) {
			this.condicionesSeleccionadas = condicionesSeleccionadas;
		}
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
			if(condicionesSeleccionadas.isEmpty() ){
				throw new NoItemSelectedException();
			}
			Metodologia metodologia = new Metodologia(nombreMetodologia, condicionesSeleccionadas);
			RepositorioDeMetodologias.agregarMetodologia(metodologia);
			condicionesRestantes.addAll(condicionesSeleccionadas);
			condicionesSeleccionadas.clear();
			
		}
		public void quitarCondicion(){
			if(condicionActualAQuitar != null){
			condicionesSeleccionadas.removeIf(condicion1 -> condicion1.equals(condicionActualAQuitar));
			condicionesRestantes.add(condicionActualAQuitar);
			ObservableUtils.firePropertyChanged(this, "condicionesRestantes");
			ObservableUtils.firePropertyChanged(this, "condicionesSeleccionadas");
			}
			else{
				throw new NoItemSelectedException();
			}
		}
		public void agregarCondicion(){
			if(condicionActualAAgregar != null){
				condicionesRestantes.removeIf(condicion1 -> condicion1.equals(condicionActualAAgregar));
				condicionesSeleccionadas.add(condicionActualAAgregar);
				ObservableUtils.firePropertyChanged(this, "condicionesRestantes");
				ObservableUtils.firePropertyChanged(this, "condicionesSeleccionadas");				
			}
			else{
				throw new NoItemSelectedException();
			}
		}
		public void cambiaronLasCondiciones() {
			ObservableUtils.firePropertyChanged(this, "condicionesRestantes");
			
		}

}
