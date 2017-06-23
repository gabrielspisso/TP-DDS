package view;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import model.Empresa;
import model.condicionMetodologia;
import viewModel.crearNuevaMetodologiaViewModel;

public class crearNuevaMetodologia extends Window<crearNuevaMetodologiaViewModel> {
	
	public crearNuevaMetodologia(WindowOwner owner, crearNuevaMetodologiaViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	
	public void createContents(Panel mainPanel) {
		setTitle("Crear una nueva Metodologia");
		
			
		Panel parte1 =new Panel(mainPanel, this);
		parte1.setLayout(new ColumnLayout(2));
		
		new Label(parte1).setText("Nombre de la metodologia:");
		new TextBox(parte1).setWidth(100);//.bindValueToProperty("nombreMetodologia");
		
		new Label(parte1).setText("Lista de condiciones:");
		new Label(parte1).setText("   ");
		
		List<condicionMetodologia> listado_Condiciones = new List<condicionMetodologia>(parte1);
		listado_Condiciones.setWidth(140);
//		listado_Empresas.bindValueToProperty("empresaActual");
//		listado_Empresas.bindItemsToProperty("empresas");
		
		Panel sub_parte1 =new Panel(parte1, this);
		sub_parte1.setLayout(new VerticalLayout());
		sub_parte1.setWidth(50);
		new Button(sub_parte1).setCaption("Crear Nueva Condicion");//.onClick(() -> new (this, new configurarMetodologiaViewModel()).open());
		new Button(sub_parte1).setCaption("Quitar Condicion"); //.onClick(() -> this.mostrarValorDeIndicadorSeleccionado());
		new Button(sub_parte1).setCaption("Finalizar Metodologia!").setBackground(Color.RED); //.onClick(() -> this.mostrarValorDeIndicadorSeleccionado());

		
		new Label(mainPanel).setText(" ----------------------------------------------------------  ");
		
		Panel parte2 =new Panel(mainPanel, this);
		parte2.setLayout(new ColumnLayout(4));
	
		new Label(parte2).setText("Nueva Condicion!");
		new Label(parte2).setText(" ");
		new Label(parte2).setText(" ");
		new Label(parte2).setText(" ");
		
		new Label(parte2).setText(" ");
		new Label(parte2).setText(" ");
		new Label(parte2).setText(" ");
		new Button(parte2).setCaption("Agregar condicion");
	}
}

