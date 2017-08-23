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
import viewModel.compararEmpresasViewModel;
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
		setTitle("Metodologias");
	
		
		mainPanel.setLayout(new ColumnLayout(3));
		
		new Label(mainPanel).setText("\n");
		new Label(mainPanel).setText("\n");		
		new Label(mainPanel).setText("\n");
		
		new Label(mainPanel).setText("Metodologias Existentes");
		new Label(mainPanel).setText("Seleccione esta opcion\npara evaluar una empresa en particular!   \n ");
		new Label(mainPanel).setText("Seleccione esta opcion\npara configurar las Metodologias!         \n ");
	

		
		List<Metodologia> listado_Empresas = new List<Metodologia>(mainPanel);
		listado_Empresas.setWidth(140);
		listado_Empresas.bindItemsToProperty("metodologias");
		
	
		Button bot_evaluarEmpresas= new Button(mainPanel);
		bot_evaluarEmpresas.setCaption("Evaluar una empresa");
		bot_evaluarEmpresas.setWidth(150);
		bot_evaluarEmpresas.setHeight(50);
		bot_evaluarEmpresas.onClick(() -> new evaluarEmpresas(this, new evaluarEmpresasViewModel()).open());
		
		Button bot_configurarMetodologia= new Button(mainPanel);
		bot_configurarMetodologia.setCaption("Crear nueva Metodologia");
		bot_configurarMetodologia.setWidth(150);
		bot_configurarMetodologia.setHeight(50);
		bot_configurarMetodologia.onClick(() -> new crearNuevaMetodologia(this, new crearNuevaMetodologiaViewModel()).open());
		

		new Label(mainPanel).setText("\n");
		new Label(mainPanel).setText("\n");		
		new Label(mainPanel).setText("\n");
		
		
		}		
}


/*
 * Finalmente, los inversionistas, en base a su experiencia y estudio, eligen (o incluso, crean) ciertos indicadores que les resultan significativos, y les imponen ciertas condiciones, que nos dicen si vale la pena invertir en una empresa, o si es mejor invertir en una o en otra.

Al conjunto de estos indicadores y condiciones se los conoce como metodologías (o métodos) de inversión, y son un elemento fundamental y diferencial de este sistema, dado que nos dan pistas sobre dónde invertir (¡y dónde no!) .  

Ejemplo: si bien la metodología de Warren Buffet no ha sido totalmente sistematizada, una posible interpretación nos dice que utiliza 6 indicadores y les aplica ciertas condiciones. 

Resumimos a continuación 4 condiciones que se pueden evaluar de forma automática:
	Maximizar ROE:  una empresa es mejor que otra si durante los últimos 10 años, su ROE fue consistentemente mejor que el de la otra.
	Minimizar el nivel de deuda: una empresa es mejor que otra si su proporción de deuda es menor
	Márgenes consistentemente crecientes: vale la pena invertir en una empresa en la que su margen durante los últimos 10 años fue siempre consistente
	Longevidad: sólo vale la pena invertir en empresas con más de 10 años. Además, una empresa es mejor que otra si es más antigua.

2.3.1 Condiciones y Metodologías definidas por el usuario

Al igual que con los indicadores, a Héctor le gustaría tener la posibilidad de crear metodologías al vuelo, sin tener que pedirle a Sabrina que modifique el programa. Las condiciones también deberían poder ser definidas por él, con cierta flexibilidad. Por ejemplo: 
Que un indicador sea mayor o menor a cierto valor, en el último año o durante los últimos N años
	Que un indicador sea mayor o menor que el de otra empresa
	Que un promedio, mediana o sumatoria de un cierto indicador sea mayor o menor a cierto valor
	Que un indicador sea sea siempre o casi siempre creciente o decreciente durante un período
*/

	
