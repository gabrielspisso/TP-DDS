package view;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.commons.model.ObservableUtils;

import Excepciones.NoItemSelectedException;
import condicionesYMetodologias.Condicion;
import model.Empresa;
import viewModel.crearCondicionViewModel;
import viewModel.crearNuevaMetodologiaViewModel;

public class crearNuevaMetodologia extends Window<crearNuevaMetodologiaViewModel> {
	
	public crearNuevaMetodologia(WindowOwner owner, crearNuevaMetodologiaViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	
	public void createContents(Panel mainPanel) {
		setTitle("Crear una nueva Metodologia");
		
			
		Panel parte1 =new Panel(mainPanel);
		parte1.setLayout(new ColumnLayout(2));
		
		new Label(parte1).setText("Nombre de la metodologia:");
		new TextBox(parte1).setWidth(100).bindValueToProperty("nombreMetodologia");
		
		new Label(parte1).setText("Descripcion de la metodologia:");
		new TextBox(parte1).bindValueToProperty("descripcion");
		
		new Label(parte1).setText("Desea guardar la metodologia?");
		CheckBox check = new CheckBox(parte1);
		check.bindValueToProperty("guardarMetodologia");
		
		Panel panel23 =new Panel(parte1);
		panel23.setLayout(new ColumnLayout(2));
		
		new Label(panel23).setText("Lista de condiciones disponibles: ");
		new Label(panel23).setText("Lista de condiciones elegidas: ");
		
		
		List<Condicion> listado_Condiciones = new List<Condicion>(panel23);
		listado_Condiciones.setWidth(140);
		listado_Condiciones.bindValueToProperty("condicionActualAAgregar");
		listado_Condiciones.bindItemsToProperty("condicionesRestantes");
		
		List<Condicion> listadoDeCondicionesSeleccionadas = new List<Condicion>(panel23);
		listadoDeCondicionesSeleccionadas.setWidth(140);
		listadoDeCondicionesSeleccionadas.bindValueToProperty("condicionActualAQuitar");
		listadoDeCondicionesSeleccionadas.bindItemsToProperty("condicionesSeleccionadas");
		
		
		Panel sub_parte1 =new Panel(parte1, this);
		sub_parte1.setLayout(new VerticalLayout());
		sub_parte1.setWidth(50);
		new Button(sub_parte1).setCaption("Crear Nueva Condicion").onClick(() ->crearNuevaCondicion() );
		new Button(sub_parte1).setCaption("Quitar Condicion").onClick(() ->this.quitarCondicion());
		new Button(sub_parte1).setCaption("Agregar Condicion").onClick(() -> this.agregarCondicion());
		
		new Button(sub_parte1).onClick(() -> this.crearMetodologia()).setCaption("Finalizar Metodologia!").setBackground(Color.RED);

		
	}

	private void crearMetodologia() {
		// TODO Auto-generated method stub
		try{
			this.getModelObject().crearMetodologia();			
			MessageBox messageBox = new MessageBox(this, Type.Information);
			messageBox.setMessage("Metodologia creada");
			messageBox.open();
		}
		catch(NoItemSelectedException ex){
			MessageBox messageBox = new MessageBox(this, Type.Error);
			messageBox.setMessage("No selecciono condiciones");
			messageBox.open();
		}
		 
	}


	public void crearNuevaCondicion(){
		new crearCondicionView(this, new crearCondicionViewModel()).open();
		this.getModelObject().cambiaronLasCondiciones();
		
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

