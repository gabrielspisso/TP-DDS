package repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.Indicador;
import model.IOIndicadores;

public class RepositorioDeIndicadores {
	private static List<Indicador> listaDeIndicadores = new ArrayList<>();

	public static List<Indicador> getListaDeIndicadores() {
		return listaDeIndicadores;
	}

	public static void agregarIndicador(Indicador indicador) {
		//listaDeIndicadores.stream().anyMatch();
		listaDeIndicadores.removeIf(x-> x.getNombre().equals(indicador.getNombre()));
		listaDeIndicadores.add(indicador);
	}
	
	public static void cargarIndicadoresDefinidos(){
		IOIndicadores.leerArchivo();
	}

}
