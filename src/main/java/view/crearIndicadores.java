package view;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
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
		
		mainPanel.setLayout(new ColumnLayout(1));
		
		Panel panel3 = new Panel(mainPanel).setLayout(new ColumnLayout(1));
		
		new Label(panel3).setText("Ejemplo de creacion: \n\n     NombreDeIndicador = FormulaDeIndicador     \nEjemplo: indicador = FDS * 4")
		.setForeground(Color.BLACK)
		.setBackground(Color.WHITE)
		.setFontSize(10);

					
		Panel panel2 = new Panel(mainPanel);
		panel2.setLayout(new ColumnLayout(2));
		
		new Label(panel2).setText("Indicador a crear:");
		
		new TextBox(panel2).setWidth(100).bindValueToProperty("indicadorActual");
		
		
		new Label(panel2).setText("Guardar permanentemente");
		CheckBox check = new CheckBox(panel2);
		check.bindEnabledToProperty("noEstaVacio");
		check.bindValueToProperty("guardarEnArchivo");

		
		Panel panel4 = new Panel(mainPanel).setLayout(new ColumnLayout(3));
		
		new Label(panel4).setText("");
		
		new Button(panel4)
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
