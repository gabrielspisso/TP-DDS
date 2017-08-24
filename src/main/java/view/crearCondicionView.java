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
import model.Indicador;
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
		
		setTitle("Crear una nueva Condicion");

		mainPanel.setLayout(new ColumnLayout(2));
	
		
		// Linea 1
		new Label(mainPanel).setText("Seleccione opcion deseada");
		Selector<String> selector = new Selector<String>(mainPanel);
				selector.bindValueToProperty("opcion");
				selector.bindItemsToProperty("opciones");
				selector.setWidth(80);
		
		// Linea 2 
		new Label(mainPanel).setText(" ");
		new Button(mainPanel).onClick(()->this.leerDescripcion()).setCaption("Leer Descripcion");

		// Linea 3
		new Label(mainPanel).setText("Ingrese nombre de indicador");
		Selector<Indicador> selectorIndicadores = new Selector<Indicador>(mainPanel);
		selectorIndicadores.setWidth(80);
		selectorIndicadores.bindValueToProperty("indicadorActual");
		selectorIndicadores.bindItemsToProperty("indicadores");
	
		// Linea 4
		new Label(mainPanel).setText("Seleccione Comportamiento");
		List<criterioDeAceptacionDeCondicion> selectorPeriodo = new List<criterioDeAceptacionDeCondicion>(mainPanel);
			selectorPeriodo.setWidth(80);
			selectorPeriodo.bindValueToProperty("comportamiento");
			selectorPeriodo.bindItemsToProperty("comportamientos");
			
		// Linea 5
		new Label(mainPanel).setText("Seleccione operatoria").bindVisibleToProperty("visibleCalculo");
		Selector<String> selector3 = new Selector<String>(mainPanel);
		selector3.setWidth(80);
		selector3.bindValueToProperty("calculo");
		selector3.bindItemsToProperty("calculos");
		selector3.bindVisibleToProperty("visibleCalculo");
	
		
		// Linea 6
		new Label(mainPanel).setText("Ingrese cantidad de años").bindVisibleToProperty("visibleCantidadDeAños");
		TextBox texboxCantAnios = new TextBox(mainPanel);
		texboxCantAnios.setWidth(80);
		texboxCantAnios.bindValueToProperty("cantidadDeAños");
		texboxCantAnios.bindVisibleToProperty("visibleCantidadDeAños");
		

		
		// linea 7
		new Label(mainPanel).setText("Ingrese el valor minimo").bindVisibleToProperty("visibleValorMinimo");
		  TextBox textboxMinimo = new TextBox(mainPanel);
		  textboxMinimo.setWidth(80);
		  textboxMinimo.bindValueToProperty("valorMinimo");
		  textboxMinimo.bindVisibleToProperty("visibleValorMinimo");
		
		// linea 8
		new Label(mainPanel).setText(" ");
		new Button(mainPanel).onClick(()->this.creacionDeCondiciones()).setCaption("Crear Condicion");
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
	
	public void leerDescripcion(){
		MessageBox messageBox;
		
		try{
			messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage("Descripcion:\n\n"+ getModelObject().getAclaracionTipo() );
		}
		catch (Exception ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage(ex.getMessage());
		}	
		messageBox.open();
	}

}
