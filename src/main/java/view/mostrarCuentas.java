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

		new FileSelector(mainPanel)
		.extensions("*.txt")
		.setCaption("Seleccionar archivo")
		.setWidth(165)
		.bindValueToProperty("rutaArchivo");
		//.extensions("*.txt"); no funciona, nu se porque, en la documentación está
		//Era porque iba arriba, antes del setCaption (santiago).
		//Buenísimo, pense que ya no tenía soporte, genial que funcione
		new Button(mainPanel).setCaption("Obtener valor de cuenta").onClick(() -> this.mostrarValorCuentaSeleccionada());
		
		new TextBox(mainPanel).setWidth(155).bindValueToProperty("rutaArchivo");
		
		
		new Button(mainPanel)
		.setCaption("Procesar archivo").onClick(() -> this.cargarArchivo());
		
		}
		
		private void cargarArchivo(){
			MessageBox messageBox;
			
			try{
				this.getModelObject().cargarEmpresas();
				messageBox = new MessageBox(this, Type.Information);
				messageBox.setMessage("Se han cargado exitosamente los datos!");
			}
			
			catch (RuntimeException ex){
				messageBox = new MessageBox(this, Type.Error);
				messageBox.setMessage("La ruta del archivo no puede estar vacía!");
			}
			messageBox.open();
		}
		
		private void mostrarValorCuentaSeleccionada(){
			MessageBox messageBox;
			
			try {
				messageBox = new MessageBox(this, Type.Information);
				messageBox.setMessage("El valor de " + this.getModelObject().getCuentaActual().getNombre() + " es " + this.getModelObject().getCuentaActual().getValor());
			}
			
			catch (RuntimeException ex){
				messageBox = new MessageBox(this, Type.Error);
				messageBox.setMessage("No has seleccionado algun campo");
			}
			
			messageBox.open();
		}
		
		
	public static void main(String[] args) {
	    //ArchivoMocking.escribirEnArchivo("archivoEmpresas.txt");
		new mostrarCuentas().startApplication();
		
		//new CargadorDeEmpresas().obtenerCuentasEmpresas();
	}
}
