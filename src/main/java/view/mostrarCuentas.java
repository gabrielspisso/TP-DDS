package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import model.Balance;
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
		mainPanel.setLayout(new ColumnLayout(2));
		// TODO Auto-generated method stub
		 new Label(mainPanel).setText("Empresa");
		 Selector<Empresa> selectorEmpresa = new Selector<Empresa>(mainPanel);
		 selectorEmpresa.bindValueToProperty("empresaActual");

		 selectorEmpresa.bindItemsToProperty("listaDeEmpresas");
		 
		 new Label(mainPanel).setText("Periodo");
		 Selector<Balance> selectorPeriodo = new Selector<Balance>(mainPanel);
		 selectorPeriodo.bindValueToProperty("balanceActual");
		 selectorPeriodo.bindItemsToProperty("balances");
		 
		 new Label(mainPanel).setText("Cuentas");
		 Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(mainPanel);
		 selectorCuenta.bindValueToProperty("cuentaActual");
		 selectorCuenta.bindItemsToProperty("cuentas");
		 
		 
		 new Label(mainPanel).setText("Valor de cuenta");
		 new Label(mainPanel).bindValueToProperty("valorActual");
		 
		 
	}
	public static void main(String[] args) {
	    new mostrarCuentas().startApplication();
	  }

}
