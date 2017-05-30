package viewModel;
import parser.Indicador;
import parser.SCANNER;
import parser.TokenYTipo;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Balance;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;

@Observable
public class crearIndicadoresViewModel {
	
	private String indicadorActual;

	public String getIndicadorActual() {
		return indicadorActual;
	}


	public void setIndicadorActual(String indicadorActual) {
		this.indicadorActual = indicadorActual;
	}
	
	public void crearIndicador(){
		try{
			SCANNER.analizarLinea(indicadorActual + ";");
			List<TokenYTipo> lista = SCANNER.obtenerTokens();
			String nombre = lista.get(0).getValor();
			
			//if(lista.subList(2, lista.size()).stream().anyMatch(predicate))
				
			Indicador nuevoIndicador = new Indicador(lista.subList(2, lista.size()), nombre);
			
			
			RepositorioDeIndicadores.agregarIndicador(nuevoIndicador);
			//System.out.println(nuevoIndicador.calcularValor(null, RepositorioDeIndicadores.getListaDeIndicadores()));
		}
		catch(RuntimeException ex){
			System.out.println(ex.getMessage());
			//System.out.println("Ingresaste mal, boludo");
		}
	}
}
