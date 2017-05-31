package viewModel;
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
import model.Indicador;
import model.IOIndicadores;

@Observable
public class crearIndicadoresViewModel {
	
	private String indicadorActual="";
	
	
	public String getIndicadorActual() {
		return indicadorActual;
	}


	public void setIndicadorActual(String indicadorActual) {
		this.indicadorActual = indicadorActual;
		ObservableUtils.firePropertyChanged(this, "noEstaVacio");
	}
	
	public void crearIndicador(){

			SCANNER.analizarLinea(indicadorActual + ";");
			List<TokenYTipo> lista = SCANNER.obtenerTokens();
			String nombre = lista.get(0).getValor();
			List<TokenYTipo> listaDeTokens =lista.subList(2, lista.size());
			if (listaDeTokens.stream().anyMatch(x->x.getValor().equals(nombre)) )
				throw new RuntimeException("Ingreso una definicion recursiva ") ;
			
			
			//if(lista.subList(2, lista.size()).stream().anyMatch(predicate))
				
			Indicador nuevoIndicador = new Indicador(lista.subList(2, lista.size()), nombre);
			
			
			RepositorioDeIndicadores.agregarIndicador(nuevoIndicador);
			//System.out.println(nuevoIndicador.calcularValor(null, RepositorioDeIndicadores.getListaDeIndicadores()));
			
			IOIndicadores.escribirArchivo(nuevoIndicador);
	
	}
	public boolean isNoEstaVacio(){
		return !indicadorActual.isEmpty();
	}

}
