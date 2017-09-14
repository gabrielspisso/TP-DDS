package view;


import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
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
import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;

import org.uqbar.arena.windows.MessageBox.Type;

import model.Balance;
import model.Indicador;
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

		mainPanel.setLayout(new ColumnLayout(3));
	
		new Label(mainPanel).setText("Nombre de la condicion: ");
		new Label(mainPanel).setText(" ");
		new TextBox(mainPanel).bindValueToProperty("nombre");
		
		// Linea 1
		new Label(mainPanel).setText("Seleccione opcion deseada: ");
		new Label(mainPanel).setText(" ");
		List<String> selector = new List<String>(mainPanel);
				selector.bindValueToProperty("opcion");
				selector.bindItemsToProperty("opciones");
				selector.setWidth(80);
		
		// Linea 2 
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Button(mainPanel).onClick(()->this.leerDescripcion()).setCaption("Leer Descripcion").setWidth(105);

		// Linea 3
		new Label(mainPanel).setText("Ingrese nombre de indicador: ");
		new Label(mainPanel).setText(" ");
		Selector<Indicador> selectorIndicadores = new Selector<Indicador>(mainPanel);
		selectorIndicadores.setWidth(80);
		selectorIndicadores.bindValueToProperty("indicadorActual");
		selectorIndicadores.bindItemsToProperty("indicadores");
	
		// Linea 4
		new Label(mainPanel).setText("Seleccione Comportamiento: ");
		new Label(mainPanel).setText(" ");
		List<criterioDeAceptacionDeCondicion> selectorPeriodo = new List<criterioDeAceptacionDeCondicion>(mainPanel);
			selectorPeriodo.setWidth(80);
			selectorPeriodo.bindValueToProperty("comportamiento");
			selectorPeriodo.bindItemsToProperty("comportamientos");
			
		// Linea 5
		new Label(mainPanel).setText("Seleccione operatoria: ").bindEnabledToProperty("visibleCalculo");
		new Label(mainPanel).setText(" ");
		List<String> selector3 = new List<String>(mainPanel);
		selector3.setWidth(80);
		selector3.bindValueToProperty("calculo");
		selector3.bindItemsToProperty("calculos");
		selector3.bindEnabledToProperty("visibleCalculo");
	
		
		// Linea 6
		new Label(mainPanel).setText("Ingrese cantidad de años: ").bindEnabledToProperty("visibleCantidadDeAños");
		new Label(mainPanel).setText(" ");
		TextBox texboxCantAnios = new TextBox(mainPanel);
		texboxCantAnios.setWidth(80);
		texboxCantAnios.bindValueToProperty("cantidadDeAños");
		texboxCantAnios.bindEnabledToProperty("visibleCantidadDeAños");
		

		
		// linea 7
		new Label(mainPanel).setText("Valor minimo de aceptacion\npara el indicador: ").bindEnabledToProperty("visibleValorMinimo");
		new Label(mainPanel).setText(" ");
		  TextBox textboxMinimo = new TextBox(mainPanel);
		  textboxMinimo.setWidth(80);
		  textboxMinimo.bindValueToProperty("valorMinimo");
		  textboxMinimo.bindEnabledToProperty("visibleValorMinimo");
		
			new Label(mainPanel).setText(" ");
			new Label(mainPanel).setText(" ");
			new Label(mainPanel).setText(" ");
		  
			
			List<Condicion> listado_Condiciones = new List<Condicion>(mainPanel);
			listado_Condiciones.setWidth(140);
			listado_Condiciones.bindEnabledToProperty("visibleListaCondiciones");
			listado_Condiciones.bindValueToProperty("condicionActualAAgregar");
			listado_Condiciones.bindItemsToProperty("listaDeCondicionesRestantes");
			
			List<Condicion> listadoDeCondicionesSeleccionadas = new List<Condicion>(mainPanel);
			listadoDeCondicionesSeleccionadas.bindEnabledToProperty("visibleListaCondiciones");
			listadoDeCondicionesSeleccionadas.setWidth(140);
			listadoDeCondicionesSeleccionadas.bindValueToProperty("condicionActualAQuitar");
			listadoDeCondicionesSeleccionadas.bindItemsToProperty("listaDeCondicionesSeleccionadas");
			
			
			Panel sub_parte1 =new Panel(mainPanel);
			sub_parte1.setWidth(50);
			new Button(sub_parte1).setCaption("Quitar Condicion").onClick(() ->this.quitarCondicion()).bindEnabledToProperty("visibleListaCondiciones");;
			new Button(sub_parte1).setCaption("Agregar Condicion").onClick(() -> this.agregarCondicion()).bindEnabledToProperty("visibleListaCondiciones");;
	
		  
		// linea 8
		new Label(mainPanel).setText(" ");
		new Label(mainPanel).setText(" ");
		new Button(mainPanel).onClick(()->this.creacionDeCondiciones()).setCaption("Crear Condicion!");
	}
	
	public void creacionDeCondiciones(){
		MessageBox messageBox;
		try{
			this.getModelObject().crearCondiciones();
			messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage("Se creo la condicion");
			messageBox.open();
			this.close();
		}
		catch (NoItemSelectedException ex){
			messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("No se han completado todos los datos para la condicion deseada!");
			messageBox.open();
		}
	}
	
	public void leerDescripcion(){
		MessageBox messageBox;
		
		messageBox = new MessageBox(this, Type.Information);
		messageBox.setMessage("Descripcion:\n\n"+ getModelObject().getAclaracionTipo() );

		messageBox.open();
	}
	public void agregarCondicion(){
		try{
			this.getModelObject().agregarCondicion();	
		}
		catch(NoItemSelectedException ex){
			MessageBox messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("No selecciono condicion a quitar");
			messageBox.open();
		}
	}
	public void quitarCondicion(){
		try{
			this.getModelObject().quitarCondicion();		
		}
		catch(NoItemSelectedException ex){
			MessageBox messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("No selecciono condicion a quitar");
			messageBox.open();
		}
	}

}
