package view;



import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;

import model.IOs;
import viewModel.menuPrincipalViewModel;
import viewModel.crearIndicadoresViewModel;
import viewModel.mostrarValoresDeEmpresasViewModel;


public class menuPrincipal extends MainWindow<menuPrincipalViewModel> {
	public menuPrincipal() {
		super(new menuPrincipalViewModel());
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		
		
setTitle("¿Dónde invierto?");
		
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("Menú Principal").setFontSize(15).setForeground(Color.RED);
		new Label(mainPanel).setText("Seleccionar la opción deseada").setFontSize(13).setForeground(Color.BLACK);
		
		new Label(mainPanel).setText(" ").setFontSize(5);
		
		//new Label(mainPanel).setText("Cargar un archivo de empresas");
		//new Button(mainPanel).setCaption("Seleccionar").onClick(() -> new cargarEmpresas(this,new cargarEmpresasViewModel()).open());
		
		

		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Mostrar el valor de una cuenta predefinida");
		Button bot_MostrarCuentas= new Button(mainPanel);
		
		bot_MostrarCuentas.setCaption("Mostrar cuentas").bindEnabledToProperty("bloq");
		bot_MostrarCuentas.onClick(() -> new mostrarValoresDeEmpresas(this,new mostrarValoresDeEmpresasViewModel()).open());
		
		new Label(mainPanel).setText(" ").setFontSize(2);

		new Label(mainPanel).setText("Crear un nuevo indicador");
		new Button(mainPanel).setCaption("Escribir formula").onClick(() -> new crearIndicadores(this,new crearIndicadoresViewModel()).open());
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Cargar un archivo de empresas");
		Panel panel2 = new Panel(mainPanel);
		panel2.setLayout(new ColumnLayout(2));
		
		FileSelector fileSelector = new FileSelector(panel2);
		fileSelector.extensions("*.txt");
		fileSelector.setCaption("Seleccionar archivo");
		fileSelector.setWidth(125);
		fileSelector.bindValueToProperty("rutaArchivo");
		fileSelector.onClick(() -> this.cargarArchivo());
		
		
		new TextBox(panel2).setWidth(125).bindValueToProperty("rutaArchivo");
		
		
		new Button(mainPanel)
		.setCaption("Procesar archivo").onClick(() -> this.cargarArchivo());
		
		try{
			IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		}
		catch(RuntimeException e){
			new Label(mainPanel).setText("El archivo de indicadores esta dañado, no se pudo cargar");
		}
	}
	

	private void cargarArchivo(){
		MessageBox messageBox;
		
		try{
			this.getModelObject().cargarEmpresas();
			messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage("Se han cargado exitosamente los datos!");
			messageBox.open();
		}
		
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("La ruta del archivo no puede estar vacÃ­a!");
			messageBox.open();
		}
	}
	
	public static void main(String[] args) {
		new menuPrincipal().startApplication();
	}
}
