package viewModel;

import org.uqbar.commons.utils.Observable;

@Observable
public class crearNuevaMetodologiaViewModel {

		private String nombreMetodologia="Ingrese nombre";

		public String getNombreMetodologia() {
			return nombreMetodologia;
		}

		public void setNombreMetodologia(String nombreMetodologia) {
			this.nombreMetodologia = nombreMetodologia;
		}
	
}
