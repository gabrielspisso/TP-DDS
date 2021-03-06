
package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.Window;
import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import viewModel.mostrarValoresDeEmpresasViewModel;

public class mostrarValoresDeEmpresas extends Window<mostrarValoresDeEmpresasViewModel>{
	
	public mostrarValoresDeEmpresas(WindowOwner owner, mostrarValoresDeEmpresasViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createContents(Panel mainPanel) {
		setTitle("Consulta de valores de cuenta");
		
		mainPanel.setLayout(new ColumnLayout(3));
		new Label(mainPanel).setText("Empresa: ");
		
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(mainPanel);
		selectorEmpresa.bindValueToProperty("empresaActual");
		selectorEmpresa.setWidth(165);
		selectorEmpresa.bindItemsToProperty("empresas");
		new Label(mainPanel).setText(" ");
		 
		new Label(mainPanel).setText("Periodo: ");
		new Label(mainPanel).setText("Cuentas: ");
		new Label(mainPanel).setText("Indicadores: ");
		
		List<Balance> selectorPeriodo = new List<Balance>(mainPanel);
		selectorPeriodo.setWidth(165);
		selectorPeriodo.setHeight(75);
		selectorPeriodo.bindValueToProperty("balanceActual");
		selectorPeriodo.bindItemsToProperty("balances");
		 
		List<Cuenta> listaCuenta = new List<Cuenta>(mainPanel);
		listaCuenta.setWidth(165);
		listaCuenta.setHeight(75);
		listaCuenta.bindValueToProperty("cuentaActual");
		listaCuenta.bindItemsToProperty("cuentas");
		listaCuenta.bindEnabledToProperty("seleccionoEmpresaYPeriodo");
		
		List<Indicador> listaIndicador = new List<Indicador>(mainPanel);
		listaIndicador.setWidth(175);
		listaIndicador.setHeight(75);
		listaIndicador.bindValueToProperty("indicadorActual");
		listaIndicador.bindItemsToProperty("indicadores");
		listaIndicador.bindEnabledToProperty("seleccionoEmpresaYPeriodo");
		Label l23 =new Label(mainPanel).setText(" ");
		

		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		
		new Button(mainPanel).setCaption("Obtener valor de la cuenta").onClick(() -> this.mostrarValorCuentaSeleccionada());
		
		new Button(mainPanel).setCaption("Obtener valor de indicador").onClick(() -> this.mostrarValorDeIndicadorSeleccionado());

		new Label(mainPanel).setText("------------------------------------------");
		new Label(mainPanel).setText("------------------------------------------");
		new Label(mainPanel).setText("------------------------------------------");
		

		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		
		
		new Label(mainPanel).setText("Resultado: ");
		new Label(mainPanel).setText("#").bindValueToProperty("resultadoIndicador");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText("#").bindValueToProperty("resultadoCuentas");
		new Label(mainPanel).setText(" ");
		
		
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		}
		
	
	private void mostrarValorDeIndicadorSeleccionado() {
		// TODO Auto-generated method stub
	
		MessageBox messageBox;
		
		try {
			datosDelIndicador();
			this.getModelObject().observerIndicador();
		}
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage(ex.getMessage());
			messageBox.open();
		}
	}
	
	private void datosDelIndicador(){
		try{
			this.getModelObject().getResultadoIndicador();
		}
		catch(Exception ex){
			throw new RuntimeException("No ingreso todos los campos necesarios para mostrar el valor de un indicador.");
		}

	}
	
	
		private void mostrarValorCuentaSeleccionada(){
			MessageBox messageBox;
			
			try {
				this.getModelObject().observerCuentas();
			}
			
			catch (RuntimeException ex){
				messageBox = new MessageBox(this, Type.Error);
				messageBox.setMessage("No has seleccionado los campos necesarios (Empresa, Balance y Cuenta)");
				messageBox.open();
			}			
		}
		
}
