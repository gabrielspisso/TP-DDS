package view;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.configurarMetodologiaViewModel;
import viewModel.evaluarEmpresasViewModel;

public class configurarMetodologia extends Window<configurarMetodologiaViewModel> {
	
	public configurarMetodologia(WindowOwner owner, configurarMetodologiaViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	
	public void createContents(Panel mainPanel) {
		setTitle("Configurar");
	}
}

