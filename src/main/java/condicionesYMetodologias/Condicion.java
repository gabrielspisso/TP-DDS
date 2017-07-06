package condicionesYMetodologias;

import model.Empresa;
import model.Indicador;

public class Condicion {
	Indicador indicador;
	public Condicion(Indicador indicador){
		this.indicador = indicador;
	}
	public boolean cumpleCondicion(Empresa empresa){
		return false;
	}
}
