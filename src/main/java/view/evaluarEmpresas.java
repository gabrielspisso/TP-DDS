package view;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.evaluarEmpresasViewModel;

public class evaluarEmpresas extends Window<evaluarEmpresasViewModel> {
	
	public evaluarEmpresas(WindowOwner owner, evaluarEmpresasViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	
	public void createContents(Panel mainPanel) {
		setTitle("Evaluar");
	}
}
