package condicionesYMetodologias;

import java.util.List;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;

public class condicionCompuesta extends Condicion{

	List<Condicion> listaDeCondiciones;
	public condicionCompuesta(Indicador indicador, criterioDeAceptacionDeCondicion criterio,List<Condicion> listaDeCondiciones){
		super(indicador,criterio,"");
		this.listaDeCondiciones = listaDeCondiciones;
	}
	public condicionCompuesta(ValoresParaEvaluar valores) {
		super(valores);
		this.listaDeCondiciones = valores.getListaDeCondiciones();
	}
	
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		return listaDeCondiciones.stream().allMatch(condicion -> condicion.cumpleCondicion(empresa, empresa1));
	}
}
