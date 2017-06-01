package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import viewModel.crearIndicadoresViewModel;

public class crearIndicadores extends Window<crearIndicadoresViewModel>{

	public crearIndicadores(WindowOwner owner, crearIndicadoresViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		setTitle("Creación de indicadores");
		
		mainPanel.setLayout(new ColumnLayout(2));
		
		
		new Label(mainPanel).setText("Indicador a crear:");
		
		new TextBox(mainPanel).setWidth(100).bindValueToProperty("indicadorActual");
		
		
		new Label(mainPanel).setText("Seleccione, si desea guardar");
		CheckBox check = new CheckBox(mainPanel);
		check.bindEnabledToProperty("noEstaVacio");
		check.bindValueToProperty("guardarEnArchivo");
		new Label(mainPanel).setText("este indicador permanentemente");
		

		
		new Button(mainPanel)
		.setCaption("Crear indicador")
		.onClick(() -> this.crearIndicador())
		.setWidth(100)
		.bindEnabledToProperty("noEstaVacio");
		
	}
	public void crearIndicador(){
		MessageBox messageBox;
		
		try {
			this.getModelObject().crearIndicador();
			messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage("Se creo correctamente!" );
		}
		
		catch (RuntimeException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage(ex.getMessage());
		}
		messageBox.open();
	}
}
