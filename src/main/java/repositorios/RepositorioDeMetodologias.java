package repositorios;

import java.util.ArrayList;
import java.util.List;

import condicionesYMetodologias.Metodologia;
import model.Indicador;

public class RepositorioDeMetodologias {
	private static List<Metodologia> listaDeMetodologias = new ArrayList<>();

	public static List<Metodologia> getListaDeMetodologias() {
		return listaDeMetodologias;
	}

	public static void agregarMetodologia(Metodologia metodologia) {
		listaDeMetodologias.removeIf(metodologia2-> metodologia2.getNombre().equals(metodologia.getNombre()));
		listaDeMetodologias.add(metodologia);
	}
	
}
