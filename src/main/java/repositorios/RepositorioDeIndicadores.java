package repositorios;

import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import parser.Indicador;

public class RepositorioDeIndicadores {
	private static List<Indicador> listaDeIndicadores = new ArrayList<>();

	public static List<Indicador> getListaDeIndicadores() {
		return listaDeIndicadores;
	}

	public static void agregarIndicador(Indicador indicador) {
		listaDeIndicadores.add(indicador);
	}
	
	
}
