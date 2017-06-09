package parser;

import java.util.List;

import model.Cuenta;
import model.Indicador;

public interface etiquetaCalculable {
	
	public double obtenerValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores);
	public String obtenerEtiqueta();
}
