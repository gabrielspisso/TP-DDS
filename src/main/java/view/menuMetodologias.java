package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

import condicionesYMetodologias.Metodologia;

import org.uqbar.arena.windows.Window;

import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import viewModel.crearNuevaMetodologiaViewModel;
import viewModel.evaluarEmpresasViewModel;
import viewModel.menuMetodologiasViewModel;
import viewModel.mostrarValoresDeEmpresasViewModel;


public class menuMetodologias extends Window<menuMetodologiasViewModel> {
	
	public menuMetodologias(WindowOwner owner, menuMetodologiasViewModel model ) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		setTitle("Menu Metodologias");
		
		mainPanel.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("Metodologias Existentes: ");	
		new Label(mainPanel).setText("\n");		
		
		List<Metodologia> listado_Empresas = new List<Metodologia>(mainPanel);
		listado_Empresas.setWidth(140);
		listado_Empresas.bindItemsToProperty("metodologias");
		new Label(mainPanel).setText("\n");

		
		new Label(mainPanel).setText("\n");
		new Label(mainPanel).setText("\n");

		Button bot_evaluarEmpresas= new Button(mainPanel);
		bot_evaluarEmpresas.setCaption("Evaluar una empresa");
		bot_evaluarEmpresas.setWidth(125);
		bot_evaluarEmpresas.setHeight(50);
		bot_evaluarEmpresas.onClick(() -> new evaluarEmpresas(this, new evaluarEmpresasViewModel()).open());
	

		Button bot_configurarMetodologia= new Button(mainPanel);
		bot_configurarMetodologia.setCaption("Crear nueva Metodologia");
		bot_configurarMetodologia.setWidth(125);
		bot_configurarMetodologia.setHeight(50);
		bot_configurarMetodologia.onClick(() ->crearNuevaMetodologia());	
		
		}
	private void crearNuevaMetodologia(){
		 new crearNuevaMetodologia(this, new crearNuevaMetodologiaViewModel()).open();
		 this.getModelObject().seCambiaronLasMetodologias();
	}
}