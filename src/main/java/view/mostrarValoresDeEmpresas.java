package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
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
		 
		new Label(mainPanel).setText("Cuentas: ");
		Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(mainPanel);
		selectorCuenta.setWidth(165);
		selectorCuenta.bindValueToProperty("cuentaActual");
		selectorCuenta.bindItemsToProperty("cuentas");
		
		
		new Label(mainPanel).setText("Indicadores: ");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(mainPanel);
		selectorIndicador.setWidth(165);
		selectorIndicador.bindValueToProperty("indicadorActual");
		selectorIndicador.bindItemsToProperty("indicadores");
		
		new Button(mainPanel).setCaption("Obtener valor de la cuenta").onClick(() -> this.mostrarValorCuentaSeleccionada());
		new Button(mainPanel).setCaption("Obtener valor de indicador").onClick(() -> this.mostrarValorDeIndicadorSeleccionado());
		
		
		//new crearIndicadores(this, new crearIndicadoresViewModel()).open();
		}
		
	
	private void mostrarValorDeIndicadorSeleccionado() {
		// TODO Auto-generated method stub
		
		MessageBox messageBox;
		
		try {
			messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage(datosDelIndicador()+
							" es " + 
							this.getModelObject().obtenerValorDeIndicador());
		}
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage(ex.getMessage());
		}

		try{
			messageBox.open();
		}
		catch(Exception ex){
			messageBox.setMessage("No has seleccionado los campos necesarios (Empresa, Balance E Indicador)");
			messageBox.open();
		}
	}
	
	private String datosDelIndicador(){

			return "La expresion del indicador es:\n"+ 
					this.getModelObject().getIndicadorActual().mostrarFormula()
					+"\nEl valor del indicador " + 
					this.getModelObject().getIndicadorActual().getNombre();

	}
	
		private void mostrarValorCuentaSeleccionada(){
			MessageBox messageBox;
			
			try {
				messageBox = new MessageBox(this, Type.Information);
				messageBox.setMessage("El valor de " + this.getModelObject().getCuentaActual().getNombre() + " es " + this.getModelObject().getCuentaActual().getValor());
			}
			
			catch (RuntimeException ex){
				messageBox = new MessageBox(this, Type.Error);
				messageBox.setMessage("No has seleccionado los campos necesarios (Empresa, Balance y Cuenta)");
			}
			
			messageBox.open();
		}
		
}
