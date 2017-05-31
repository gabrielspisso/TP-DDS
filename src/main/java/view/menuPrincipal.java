package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MainWindow;

import parser.Token;
import viewModel.cargarArchivoViewModel;
import viewModel.crearIndicadoresViewModel;
import viewModel.mostrarCuentaViewModel;
import viewModel.mostrarIndicadorViewModel;

public class menuPrincipal extends MainWindow<mostrarCuentaViewModel> {
	public menuPrincipal() {
		super(new mostrarCuentaViewModel());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		setTitle("Menú principal para inversionistas");
		
		mainPanel.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("Ver valor de un indicador:");
		new Label(mainPanel).setText("ver valor de un indicador:");
		new Button(mainPanel).setCaption("Ver el valor de un indicador").onClick(() -> new mostrarIndicador(this, new mostrarIndicadorViewModel()).open());
		new Button(mainPanel).setCaption("Ver el valor de una cuentas").onClick(() -> new mostrarCuentas(this,new mostrarCuentaViewModel()).open());
		
		
		new Label(mainPanel).setText("Cargar un archivo:");
		new Label(mainPanel).setText("Crear un indicador:");

		new Button(mainPanel).setCaption("Cargar un archivo de empresas").onClick(() -> new cargarArchivo(this,new cargarArchivoViewModel()).open());
		new Button(mainPanel).setCaption("Crear indicador ").onClick(() -> new crearIndicadores(this,new crearIndicadoresViewModel()).open());
		
	}
	public static void main(String[] args) {
	    //ArchivoMocking.escribirEnArchivo("archivoEmpresas.txt");
		new menuPrincipal().startApplication();
		//new CargadorDeEmpresas().obtenerCuentasEmpresas();
	}
}
