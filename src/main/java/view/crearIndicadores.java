package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import model.ArchivoMocking;
import model.Balance;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import viewModel.crearIndicadoresViewModel;
import viewModel.mostrarCuentaViewModel;

public class crearIndicadores extends Window<crearIndicadoresViewModel>{

	public crearIndicadores(WindowOwner owner, crearIndicadoresViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		setTitle("Consulta de valores de cuenta");
		
		mainPanel.setLayout(new ColumnLayout(2));
		new Label(mainPanel).setText("Empresa: ");
		
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(mainPanel);
		selectorEmpresa.bindValueToProperty("empresaActual");
		selectorEmpresa.setWidth(165);
		selectorEmpresa.bindItemsToProperty("empresas");
		 
		new Label(mainPanel).setText("Periodo: ");
		
		Selector<Balance> selectorPeriodo = new Selector<Balance>(mainPanel);
		selectorPeriodo.setWidth(165);
		selectorPeriodo.bindValueToProperty("balanceActual");
		selectorPeriodo.bindItemsToProperty("balances");
		
		
		
		new Label(mainPanel).setText("Indicador a crear:");
		
		new TextBox(mainPanel).bindValueToProperty("indicadorActual");
		
		new Button(mainPanel).setCaption("Crear Indicador").onClick(() -> this.getModelObject().crearIndicador());
		//.extensions("*.txt"); no funciona, nu se porque, en la documentación está
		//Era porque iba arriba, antes del setCaption (santiago).
		//Buenísimo, pense que ya no tenía soporte, genial que funcione
		
		//new Button(mainPanel).setCaption("Obtener valor de cuenta").onClick(() -> this.getModelObject().this.mostrarValorCuentaSeleccionada(this));
		
		
		//new Button(mainPanel).setCaption("Obtener valor de cuenta").onClick(() -> this.mostrarValorCuentaSeleccionada());
		

		
	}
}
