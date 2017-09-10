package condicionesYMetodologias;

import java.util.List;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;

public class condicionCompuesta extends Condicion{
	String nombre;
	List<Condicion> listaDeCondiciones;
	public condicionCompuesta(String nombre,List<Condicion> listaDeCondiciones){
		this.nombre = nombre;
		this.listaDeCondiciones = listaDeCondiciones;
	}
	public condicionCompuesta(ValoresParaEvaluar valores) {
		nombre = valores.getNombre();
		this.listaDeCondiciones = valores.getListaDeCondiciones();
	}
	
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		return listaDeCondiciones.stream().allMatch(condicion -> condicion.cumpleCondicion(empresa, empresa1));
	}
	public String toString(){
		return nombre;
	}
}
