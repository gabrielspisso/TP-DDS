package model.condicionesYMetodologias;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;

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
	
	protected Condicion(Indicador indicador2, criterioDeAceptacionDeCondicion criterio2, String nombre2){
		this.indicador = indicador2;
		this.criterio = criterio2;
		this.nombre = nombre2;
	}
	
	@Override
	public String toString(){
		return nombre;
	}
	
	public boolean cumpleCondicion(Empresa empresa) {
		try{
			return this.cumpleLaCondicion(empresa);
		}
		catch(IdentificadorInexistente oo)
		{
			return false;
		}
	}

	protected abstract boolean cumpleLaCondicion(Empresa empresa);
	
	protected boolean existeEseIndicadorParaEstaEmpresa(Empresa empresa, Indicador indicador){
		try{
			indicador.calcularValor(empresa.getBalances().get(0).getCuentas());
			return true;
		}
		catch(RuntimeException ex){
			return false;
		}
	}

	public boolean cumpleLaCondicionEmpresarial(Empresa empresa, List<Empresa> todasLasEmpresas) {
		return false;
	}
}
