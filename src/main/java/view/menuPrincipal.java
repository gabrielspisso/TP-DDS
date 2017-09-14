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

import Excepciones.RecursiveException;
import model.CargadorDeEmpresas;
import model.IOs;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;
import viewModel.menuPrincipalViewModel;
import viewModel.crearIndicadoresViewModel;
import viewModel.crearNuevaMetodologiaViewModel;
import viewModel.evaluarEmpresasViewModel;
import viewModel.importarExportarArchivosViewModel;
import viewModel.mostrarValoresDeEmpresasViewModel;


@SuppressWarnings("serial")
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
		
		new Label(mainPanel).setText("Carga Correcta de las Empresas!").setFontSize(9).setForeground(Color.GREEN).bindVisibleToProperty("bloq");
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Mostrar el valor de una cuenta predefinida");
		Button bot_MostrarCuentas= new Button(mainPanel);
		
		bot_MostrarCuentas.setCaption("Mostrar valores de la Empresa");//.bindEnabledToProperty("bloq");
		bot_MostrarCuentas.onClick(() -> new mostrarValoresDeEmpresas(this,new mostrarValoresDeEmpresasViewModel()).open());
		
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Crear un nuevo indicador");
		new Button(mainPanel).setCaption("Escribir formula").onClick(() -> new crearIndicadores(this,new crearIndicadoresViewModel()).open());
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		
		new Label(mainPanel).setText("Menu de Metodologias");
		
		Panel parte1 =new Panel(mainPanel);
		parte1.setLayout(new ColumnLayout(2));	
		Button bot_evaluarEmpresas= new Button(parte1);
		bot_evaluarEmpresas.setCaption("Evaluar una empresa");
		bot_evaluarEmpresas.setWidth(125);
		bot_evaluarEmpresas.onClick(() -> new evaluarEmpresas(this, new evaluarEmpresasViewModel()).open());
	
		Button bot_configurarMetodologia= new Button(parte1);
		bot_configurarMetodologia.setCaption("Crear Metodologia");
		bot_configurarMetodologia.setWidth(125);
		bot_configurarMetodologia.onClick(() ->crearNuevaMetodologia());	
		
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		
		
		new Label(mainPanel).setText("Importar/Exportar archivos");
		
		Button bot_impExpArchivos = new Button(mainPanel);
		bot_impExpArchivos.setCaption("Importar Archivos o exportar");
		bot_impExpArchivos.setWidth(125);
		bot_impExpArchivos.onClick(() -> new importarExportarArchivos(this, new importarExportarArchivosViewModel()).open());
		

		new Label(mainPanel).setText(" ").setFontSize(2);
		new Label(mainPanel).setText(" ").setFontSize(2);
		new Label(mainPanel).setText(" ").setFontSize(2);
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Desde aca abajo vuela todo y va a otra ventana \\|/").setFontSize(8);
		
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
		
		
		new Button(mainPanel).setCaption("Procesar archivo").onClick(() -> this.cargarArchivo());

		IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		
		RepositorioDeEmpresas.traerEmpresasDeLaDB();
	}
	
	private void crearNuevaMetodologia(){
		 new crearNuevaMetodologia(this, new crearNuevaMetodologiaViewModel()).open();
	}
	
	private void cargarArchivo(){
		MessageBox messageBox;
		
		try{
			this.getModelObject().cargarEmpresas();
		}
		
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("La ruta del archivo es incorrecta!");
			messageBox.open();
		}
	}
	
	public static void main(String[] args) {
		
		new menuPrincipal().startApplication();
	}
}