package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.Metodologia;
import model.IOs;
import repositorios.RepositorioDeCondiciones;
import repositorios.RepositorioDeMetodologias;

@Observable
public class crearNuevaMetodologiaViewModel {

		private String nombreMetodologia="Ingrese nombre";
		private List<Condicion> condicionesRestantes = RepositorioDeCondiciones.mostrarListaDeCondiciones();
		private List<Condicion> condicionesSeleccionadas = new ArrayList<Condicion>();
		private Condicion condicionActualAAgregar;
		private Condicion condicionActualAQuitar;
		private String descripcion;
		private boolean guardarMetodologia;
		
		public void setCondicionesRestantes(List<Condicion> condicionesRestantes) {
			this.condicionesRestantes = condicionesRestantes;
		}
		public void setCondicionesSeleccionadas(List<Condicion> condicionesSeleccionadas) {
			this.condicionesSeleccionadas = condicionesSeleccionadas;
		}
			
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
			
		//	List<Condicion> listitaCondiciones = new ArrayList<Condicion>();
			
			Metodologia metodologia = new Metodologia(nombreMetodologia, descripcion, condicionesSeleccionadas);
						
			RepositorioDeMetodologias.agregarMetodologia(metodologia);
			condicionesRestantes.addAll(condicionesSeleccionadas);
			condicionesSeleccionadas =  new ArrayList<Condicion>();
			
			if(guardarMetodologia)
				IOs.guardarMetodologias(metodologia, "archivoMetodologias.txt");
			
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
				ObservableUtils.firePropertyChanged(this,"condicionesRestantes");
				ObservableUtils.firePropertyChanged(this,"condicionesSeleccionadas");				
			}
			else{
				throw new NoItemSelectedException();
			}
		}
		public void cambiaronLasCondiciones() {
			List<Condicion> listaDeCondicionesDelRepo = RepositorioDeCondiciones.mostrarListaDeCondiciones();
			//No programo a prueba de boludeces
			if(listaDeCondicionesDelRepo.size()>0){
				Condicion ultimaCondicionAgregada = listaDeCondicionesDelRepo.get(listaDeCondicionesDelRepo.size()-1);
				condicionesRestantes.add(ultimaCondicionAgregada);
				ObservableUtils.firePropertyChanged(this,"condicionesRestantes");				
			}
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		public boolean isGuardarMetodologia() {
			return guardarMetodologia;
		}
		
		public void setGuardarMetodologia(boolean guardarMetodologia) {
			this.guardarMetodologia = guardarMetodologia;
		}
		
		

}
