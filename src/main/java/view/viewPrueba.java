package view;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.evaluarEmpresasViewModel;
import viewModel.mostrarValoresDeEmpresasViewModel;

public class viewPrueba extends Window<evaluarEmpresasViewModel>{

	public viewPrueba(WindowOwner owner, evaluarEmpresasViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		new Label(mainPanel).bindValueToProperty("nombre");
	}

}
