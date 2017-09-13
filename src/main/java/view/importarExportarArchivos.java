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
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
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




public class importarExportarArchivos extends Window<importarExportarArchivosViewModel> {
	
	public importarExportarArchivos(WindowOwner owner, importarExportarArchivosViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		
		
setTitle("Exportar/Importar");
		
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setText("Mostrar el valor de una cuenta predefinida");

		
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Exportar");
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		
		new Label(mainPanel).setText("Importar");
		
	
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		new Label(mainPanel).setText("Cargar un archivo de empresas");
	
		Panel panel2 = new Panel(mainPanel);
		panel2.setLayout(new ColumnLayout(2));
		
		FileSelector fileSelector = new FileSelector(panel2);
		fileSelector.extensions("*.txt");
		fileSelector.setCaption("Seleccionar archivo");
		fileSelector.setWidth(125);
//		fileSelector.bindValueToProperty("rutaArchivo");
		//fileSelector.onClick(() -> this.cargarArchivo());
		
		
	//	new TextBox(panel2).setWidth(125).bindValueToProperty("rutaArchivo");
		
		
	//	new Button(mainPanel)
	//	.setCaption("Procesar archivo").onClick(() -> this.cargarArchivo());
		

	//	IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
		
		//try {
	//	RepositorioDeEmpresas.traerEmpresasDeLaDB();
		//RepositorioDeIndicadores.traerIndicadoresDeLaDB();
					
		//}
		//catch (Exception e) {
			
		//}
	}
}
	
	
	/*private void cargarArchivo(){
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
	

*/