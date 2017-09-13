package condicionesYMetodologias;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Balance;
import model.Empresa;
import model.Indicador;

@Entity
public class condicionCompuesta extends Condicion{
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	List<Condicion> listaDeCondiciones;
	public condicionCompuesta(String nombre,List<Condicion> listaDeCondiciones){
		super(nombre);
		this.listaDeCondiciones = listaDeCondiciones;
	}
	public condicionCompuesta(ValoresParaEvaluar valores) {
		super(valores.getNombre());
		this.listaDeCondiciones = valores.getListaDeCondiciones();
	}
	@Override
	public boolean seCumpleLaCondicion(Empresa empresa, Empresa empresa1){
		return listaDeCondiciones.stream().allMatch(condicion -> condicion.cumpleCondicion(empresa, empresa1));
	}
	public String toString(){
		return nombre;
	}
	
}
