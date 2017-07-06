package view;


import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import viewModel.compararEmpresasViewModel;
import viewModel.crearCondicionViewModel;

public class crearCondicionView extends Window<crearCondicionViewModel> {

	public crearCondicionView(WindowOwner owner, crearCondicionViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		new Label(mainPanel).setText("Seleccione opcion deseada");
		Selector<String> selector = new Selector<String>(mainPanel);
		selector.bindValueToProperty("opcion");
		selector.bindItemsToProperty("opciones");
		mainPanel.setLayout(new ColumnLayout(2));
		new Label(mainPanel).setText("Ingrese nombre de indicador");
		new TextBox(mainPanel).bindValueToProperty("nombreDeIndicador");

		new Label(mainPanel).setText("Ingrese el valor minimo");
		new TextBox(mainPanel).bindValueToProperty("valorMinimo");

		new Label(mainPanel).setText("es taxativas?");
		new CheckBox(mainPanel).bindValueToProperty("taxatividad");

		new Label(mainPanel).setText("Ingrese cantidad de años").bindVisibleToProperty("visibleCantidadDeAños");
		TextBox t1 = new TextBox(mainPanel);
		t1.bindValueToProperty("cantidadDeAños");
		t1.bindVisibleToProperty("visibleCantidadDeAños");

		new Label(mainPanel).setText("Ingrese operatoria deseada").bindVisibleToProperty("visibleCalculo");
		TextBox t2 =new TextBox(mainPanel);
		t2.bindValueToProperty("calculo");
		t2.bindVisibleToProperty("visibleCalculo");
		new Label(mainPanel).setText("Ingrese comportamiento deseada").bindVisibleToProperty("visibleComportamiento");
		TextBox t3 =new TextBox(mainPanel);
		t3.bindValueToProperty("comportamiento");
		t3.bindVisibleToProperty("visibleComportamiento");
		
		
		new Button(mainPanel).onClick(()->this.creacionDeCondiciones()).setCaption("Crear Metodologia");
	}
	public void creacionDeCondiciones(){
		MessageBox messageBox;
		try{
			this.getModelObject().crearCondiciones();
			messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage("Se creo la condicion");
	
		}
		catch (Exception ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage(ex.getMessage());
		}
		
		messageBox.open();
	}

}
