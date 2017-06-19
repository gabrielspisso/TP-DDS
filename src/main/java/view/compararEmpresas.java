package view;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.compararEmpresasViewModel;
import viewModel.menuMetodologiasViewModel;

public class compararEmpresas  extends Window<compararEmpresasViewModel> {
	
	public compararEmpresas(WindowOwner owner, compararEmpresasViewModel model ) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}
	
	public void createContents(Panel mainPanel) {
		setTitle("Comparar Empresas");
	}
}
