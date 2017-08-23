package view;


import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import Calculos.Calculo;
import Calculos.criterioDeAceptacionDeCondicion;

import org.uqbar.arena.windows.MessageBox.Type;

import model.Balance;
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
		new Label(mainPanel).setText("Seleccione Comportamiento");
		List<criterioDeAceptacionDeCondicion> selectorPeriodo = new List<criterioDeAceptacionDeCondicion>(mainPanel);

		selectorPeriodo.bindValueToProperty("comportamiento");
		selectorPeriodo.bindItemsToProperty("comportamientos");
		new Label(mainPanel).setText("Ingrese el valor minimo");
		new TextBox(mainPanel).bindValueToProperty("valorMinimo");
		
		new Label(mainPanel).setText("Seleccione operatoria").bindVisibleToProperty("visibleCalculo");
		Selector<String> selector3 = new Selector<String>(mainPanel);
		selector3.bindValueToProperty("calculo");
		selector3.bindItemsToProperty("calculos");
		selector3.bindVisibleToProperty("visibleCalculo");

		new Label(mainPanel).setText("Ingrese cantidad de años").bindVisibleToProperty("visibleCantidadDeAños");
		TextBox t1 = new TextBox(mainPanel);
		t1.bindValueToProperty("cantidadDeAños");
		t1.bindVisibleToProperty("visibleCantidadDeAños");

		
	
		
		
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
		this.close();
	}

}
