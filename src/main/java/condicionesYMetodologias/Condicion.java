package condicionesYMetodologias;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import Calculos.criterioDeAceptacionDeCondicion;
import Excepciones.IdentificadorInexistente;
import model.Empresa;
import model.Indicador;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Condicion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	protected String nombre;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected Indicador indicador;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected criterioDeAceptacionDeCondicion criterio;

	
	protected Condicion(){
		
	}
	
	protected Condicion(String nombre, Indicador indicador, criterioDeAceptacionDeCondicion criterio){
		this.nombre = nombre;
		this.indicador = indicador;
		this.criterio = criterio;
	}
	
	public Condicion(ValoresParaEvaluar valores) {
		this.nombre = valores.getNombre();
		this.indicador = valores.getIndicadorActual();
		this.criterio = valores.getComportamiento();
	}

	public abstract boolean esCondicionDeFiltrado();
	
	
	@Override
	public String toString(){
		return nombre;
	}
	protected boolean existeEseIndicadorParaEstaEmpresa(Empresa empresa, Indicador indicador){
		try{
			indicador.calcularValor(empresa.getBalances().get(0).getCuentas());
			return true;
		}
		catch(RuntimeException ex){
			return false;
		}
	}
}
