package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import viewModel.cargarArchivoViewModel;

public class cargarArchivo extends Window<cargarArchivoViewModel>{

	public cargarArchivo(WindowOwner owner, cargarArchivoViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		new FileSelector(mainPanel)
		.extensions("*.txt")
		.setCaption("Seleccionar archivo")
		.setWidth(165)
		.bindValueToProperty("rutaArchivo");
		//.extensions("*.txt"); no funciona, nu se porque, en la documentación está
		//Era porque iba arriba, antes del setCaption (santiago).
		//Buenísimo, pense que ya no tenía soporte, genial que funcione
		

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
	
}
