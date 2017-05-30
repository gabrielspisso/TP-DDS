package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MainWindow;

import parser.Token;
import viewModel.cargarArchivoViewModel;
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
		
		mainPanel.setLayout(new ColumnLayout(3));
		
		new Label(mainPanel).setText("Indicador a crear:");
		new Label(mainPanel).setText("Indicador a crear:");
		new Label(mainPanel).setText("Indicador a crear:");
		
		
		new Button(mainPanel).setCaption("Ver el valor de una cuentas").onClick(() -> new mostrarCuentas(this,new mostrarCuentaViewModel()).open());
		new Button(mainPanel).setCaption("Ver el valor de un indicador").onClick(() -> new mostrarIndicador(this, new mostrarIndicadorViewModel()).open());
		new Button(mainPanel).setCaption("Cargar un archivo de empresas").onClick(() -> new cargarArchivo(this,new cargarArchivoViewModel()).open());
		
	}
	public static void main(String[] args) {
	    //ArchivoMocking.escribirEnArchivo("archivoEmpresas.txt");
		new menuPrincipal().startApplication();
		//new CargadorDeEmpresas().obtenerCuentasEmpresas();
	}
}
