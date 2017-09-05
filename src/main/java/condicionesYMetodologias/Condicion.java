package condicionesYMetodologias;

import model.Empresa;

public interface Condicion {
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1);
}
