package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.MainWindow;

import model.ArchivoMocking;
import model.Balance;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import viewModel.mostrarCuentaViewModel;


public class mostrarCuentas extends MainWindow<mostrarCuentaViewModel>{

	public mostrarCuentas() {
		super(new mostrarCuentaViewModel());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		setTitle("Consulta de valores de cuenta");
		
		mainPanel.setLayout(new ColumnLayout(2));
		new Label(mainPanel).setText("Empresa: ");
		
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(mainPanel);
		selectorEmpresa.bindValueToProperty("empresaActual");
		selectorEmpresa.setWidth(125);
		selectorEmpresa.bindItemsToProperty("empresas");
		 
		new Label(mainPanel).setText("Periodo: ");
		
		Selector<Balance> selectorPeriodo = new Selector<Balance>(mainPanel);
		selectorPeriodo.setWidth(125);
		selectorPeriodo.bindValueToProperty("balanceActual");
		selectorPeriodo.bindItemsToProperty("balances");
		 
		new Label(mainPanel).setText("Cuentas: ");
		Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(mainPanel);
		selectorCuenta.setWidth(125);
		selectorCuenta.bindValueToProperty("cuentaActual");
		selectorCuenta.bindItemsToProperty("cuentas");
		 
		new Button(mainPanel)
		.setCaption("Obtener datos desde archivo").onClick(() -> getModelObject().cargarEmpresas(this));
		
		new Button(mainPanel).setCaption("Obtener valor de cuenta").onClick(() -> getModelObject().mostrarValorCuentaSeleccionada(this));

		  
	}
	public static void main(String[] args) {
	    //ArchivoMocking.escribirEnArchivo("archivoEmpresas.txt");
		new mostrarCuentas().startApplication();
		
		//new CargadorDeEmpresas().obtenerCuentasEmpresas();
	}

}
