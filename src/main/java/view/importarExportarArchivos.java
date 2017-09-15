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
		
		mainPanel.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		//////// Arranca aca la importacion exportacion de empresas
		
		
		new Label(mainPanel).setText("Empresas:").setFontSize(10);
		new Label(mainPanel).setText(" ").setFontSize(2);
		
		//exportar empresas
				//  Ingrese nombre:  textbook
				//  _espacio_       Exportar
		

		new Label(mainPanel).setText("Nombre del archivo:");
		TextBox nombreEmpresa = new TextBox(mainPanel);
		nombreEmpresa.setWidth(125).bindValueToProperty("nombre_ArchivoEmpresa");
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		Button exp_Empresas = new Button(mainPanel);
		exp_Empresas.setCaption("Exportar Empresas");
		exp_Empresas.setWidth(125);
		//exp_Empresas.onClick(() -> aca iria un metodo que te permite exportar).open());
		

		new Label(mainPanel).setText(" ").setFontSize(1);
		new Label(mainPanel).setText(" ").setFontSize(1);
	
		// Importar
			// Buscar archivo - txbox
			// _espacio_  - importar
		
		FileSelector selectorEmpresas = new FileSelector(mainPanel);
		selectorEmpresas.extensions("*.txt");
		selectorEmpresas.setCaption("Buscar archivo");
		selectorEmpresas.setWidth(125);
		selectorEmpresas.bindValueToProperty("rutaArchivo");
		selectorEmpresas.onClick(() -> this.cargarArchivoE());
		new TextBox(mainPanel).setWidth(125).bindValueToProperty("rutaArchivo");

		new Label(mainPanel).setText(" ");
		new Button(mainPanel).setCaption("Importar archivo").onClick(() -> this.cargarArchivoE());
	//	IOs.leerIndicadoresDeArchivo("archivoIndicadores.txt");
	//	RepositorioDeEmpresas.traerEmpresasDeLaDB();

	// ------ Fin de las empresas --------- //


	// ------ Comienzo de las metodologias --------- //
		
		
	new Label(mainPanel).setText(" ").setFontSize(5);
	new Label(mainPanel).setText(" ").setFontSize(5);
		
	new Label(mainPanel).setText("Metodologias:").setFontSize(10);
	new Label(mainPanel).setText(" ").setFontSize(2);
		
	//exportar metodologias
			//  Ingrese nombre:  textbook
			//  _espacio_       Exportar
		

	new Label(mainPanel).setText("Nombre del archivo:");
	TextBox nombreMetodologia = new TextBox(mainPanel);
	nombreMetodologia.setWidth(125).bindValueToProperty("nombre_ArchivoMetodologia");
		
		new Label(mainPanel).setText(" ").setFontSize(2);
		Button exp_Metodologia = new Button(mainPanel);
		exp_Metodologia.setCaption("Exportar Metodologias");
		exp_Metodologia.setWidth(125);
		//exp_Metodologia.onClick(() -> aca iria un metodo que te permite exportar).open());
		

		new Label(mainPanel).setText(" ").setFontSize(1);
		new Label(mainPanel).setText(" ").setFontSize(1);
	
		// Importar
			// Buscar archivo - txbox
			// _espacio_  - importar
		
		FileSelector selectorMetodologias = new FileSelector(mainPanel);
		selectorMetodologias.extensions("*.txt");
		selectorMetodologias.setCaption("Buscar archivo");
		selectorMetodologias.setWidth(125);
		selectorMetodologias.bindValueToProperty("rutaArchivoMetodologias");
		selectorMetodologias.onClick(() -> this.cargarArchivoM());
		new TextBox(mainPanel).setWidth(125).bindValueToProperty("rutaArchivoMetodologias");

		new Label(mainPanel).setText(" ");
		new Button(mainPanel).setCaption("Importar archivo").onClick(() -> this.cargarArchivoM());

		new Label(mainPanel).setText(" ").setFontSize(1);
		new Label(mainPanel).setText(" ").setFontSize(1);
		
		// --------- FIN importar/exportar metodologias ----------- //
		
		
		

		// ------ Comienzo de los indicadores --------- //
		
		
		new Label(mainPanel).setText(" ").setFontSize(5);
		new Label(mainPanel).setText(" ").setFontSize(5);
			
		new Label(mainPanel).setText("Indicadores:").setFontSize(10);
		new Label(mainPanel).setText(" ").setFontSize(2);
			
		//exportar metodologias
				//  Ingrese nombre:  textbook
				//  _espacio_       Exportar
			

		new Label(mainPanel).setText("Nombre del archivo:");
		TextBox nombreIndicador = new TextBox(mainPanel);
		nombreIndicador.setWidth(125).bindValueToProperty("nombre_ArchivoIndicadores");
			
			new Label(mainPanel).setText(" ").setFontSize(2);
			Button exp_Indicador = new Button(mainPanel);
			exp_Indicador.setCaption("Exportar Indicadores");
			exp_Indicador.setWidth(125);
			//exp_Indicador.onClick(() -> aca iria un metodo que te permite exportar).open());
			

			new Label(mainPanel).setText(" ").setFontSize(1);
			new Label(mainPanel).setText(" ").setFontSize(1);
		
			// Importar
				// Buscar archivo - txbox
				// _espacio_  - importar
			
			FileSelector selectorIndicador = new FileSelector(mainPanel);
			selectorIndicador.extensions("*.txt");
			selectorIndicador.setCaption("Buscar archivo");
			selectorIndicador.setWidth(125);
			selectorIndicador.bindValueToProperty("rutaArchivoIndicadores");
			selectorIndicador.onClick(() -> this.cargarArchivoI());
			new TextBox(mainPanel).setWidth(125).bindValueToProperty("rutaArchivoIndicadores");

			new Label(mainPanel).setText(" ");
			new Button(mainPanel).setCaption("Importar archivo").onClick(() -> this.cargarArchivoI());

			new Label(mainPanel).setText(" ").setFontSize(1);
			new Label(mainPanel).setText(" ").setFontSize(1);
			
			// --------- FIN importar/exportar metodologias ----------- //
			
			
			
}
	
	
	private void cargarArchivoE(){
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
	
	private void cargarArchivoM(){
		MessageBox messageBox;
		
		try{
			this.getModelObject().cargarMetodologias();
		}
		
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("La ruta del archivo es incorrecta!");
			messageBox.open();
		}
	}
	private void cargarArchivoI(){
		MessageBox messageBox;
		
		try{
			this.getModelObject().cargarIndicadores();
		}
		
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("La ruta del archivo es incorrecta!");
			messageBox.open();
		}
	}
}