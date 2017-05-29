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
import parser.Indicador;
import viewModel.crearIndicadoresViewModel;
import viewModel.mostrarCuentaViewModel;
import viewModel.mostrarIndicadorViewModel;

public class mostrarIndicador extends Window<mostrarIndicadorViewModel>{
	
	public mostrarIndicador(WindowOwner owner, mostrarIndicadorViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
setTitle("Consulta de valores de indicadores");
		
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
		 
		new Label(mainPanel).setText("Indicadores: ");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(mainPanel);
		selectorIndicador.setWidth(165);
		selectorIndicador.bindValueToProperty("indicadorActual");
		selectorIndicador.bindItemsToProperty("indicadores");
		
		new Button(mainPanel).setCaption("Obtener valor de cuenta").onClick(() -> this.mostrarValorDeIndicadorSeleccionado());
	
		
		
		
		
		new Button(mainPanel)
		.setCaption("abrir").onClick(() -> new crearIndicadores(this, new crearIndicadoresViewModel()).open());

		
	}

	private Object mostrarValorDeIndicadorSeleccionado() {
		// TODO Auto-generated method stub
		return null;
	}
	
}