package viewModel;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import condicionesYMetodologias.Metodologia;
import repositorios.RepositorioDeMetodologias;

@Observable
public class menuMetodologiasViewModel {
	
	
	public List<Metodologia> getMetodologias(){
		return RepositorioDeMetodologias.getListaDeMetodologias();
	}

	public void seCambiaronLasMetodologias() {
		ObservableUtils.firePropertyChanged(this,"metodologias");
		
	}
}
