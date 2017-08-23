package view;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import condicionesYMetodologias.Metodologia;

import org.uqbar.arena.widgets.List;

import model.Empresa;
import viewModel.evaluarEmpresasViewModel;

public class evaluarEmpresas extends Window<evaluarEmpresasViewModel> {
	
	public evaluarEmpresas(WindowOwner owner, evaluarEmpresasViewModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	}

	
	public void createContents(Panel mainPanel) {
		setTitle("Evaluar");
		
	
		/// ----  Primer Panel ---- ///
		
		Panel parte1 =new Panel(mainPanel, this);
		
		parte1.setLayout(new ColumnLayout(3));	

		//*** Linea 0
		new Label(parte1).setText("Seleccion de empresas: ");
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		
		//*** Linea 1
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		
		//*** Linea2
		new Label(parte1).setText("Seleccione todas las empresas\nque desee comparar:            ");
		new Label(parte1).setText("                               ");
		new Label(parte1).setText("Empresas seleccionadas:        ");
		
		//*** Liena 3
		List<Empresa> listado_Empresas = new List<Empresa>(parte1);
		listado_Empresas.setWidth(140);
//		listado_Empresas.bindValueToProperty("empresaActual");
//		listado_Empresas.bindItemsToProperty("empresas");
		
		Panel sub_parte1 =new Panel(parte1, this);
		sub_parte1.setLayout(new VerticalLayout());
		sub_parte1.setWidth(50);
		new Button(sub_parte1).setCaption("Añadir -->");//.onClick(() -> this.mostrarValorCuentaSeleccionada());
		new Button(sub_parte1).setCaption("<-- Quitar"); //.onClick(() -> this.mostrarValorDeIndicadorSeleccionado());

		List<Empresa> listado_EmpresasAEvaluar = new List<Empresa>(parte1);
		listado_EmpresasAEvaluar.setWidth(140);
	
		//*** Linea 4
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		
		//*** Linea 5
		new Label(parte1).setText(" ");
		new Label(parte1).setText("Seleccione la metodologia a evaluar:");
		
		Selector<Metodologia> selector_Metodologia = new Selector<Metodologia>(parte1);
		selector_Metodologia.setWidth(165);
//		selectorPeriodo.bindValueToProperty("");
//		selectorPeriodo.bindItemsToProperty("");

		//*** Linea 6
		new Label(parte1).setText(" ");
		new Button(parte1).setCaption("Ver detalle Metodologia");//.onClick(() -> this.mostrarValorCuentaSeleccionada());
		new Button(parte1).setCaption("Aplicar evaluacion"); //.onClick(() -> this.mostrarValorDeIndicadorSeleccionado());

		//*** Linea 7
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		new Label(parte1).setText("   ");
		
		/// ---- Fin Primer Panel ---- ///
		
		
		
		/// ---- Segundo Panel ---- ///
		
		Panel parte2 =new Panel(mainPanel, this);
		
		parte2.setLayout(new ColumnLayout(1));	


		new Label(parte2).setText("   ");
		

		new Label(parte2).setText("Resultado!");
		new Label(parte2).setText("   ");
		
		new Label(parte2).setText("Aca va la tabla de resultados");
//		Table<Metodologia> table_Resultados = new Table<Metodologia>(parte2, Metodologia.class);
//	    tableNotas.setHeight(500);
//	    tableNotas.setWidth(350);
		
		
	}
}