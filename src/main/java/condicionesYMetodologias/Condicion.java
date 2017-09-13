package condicionesYMetodologias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import Excepciones.IdentificadorInexistente;
import model.Empresa;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Condicion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	protected String nombre;
	
	protected Condicion(){
		
	}
	
	protected Condicion(String nombre){
		this.nombre = nombre;
	}
	
	public boolean cumpleCondicion(Empresa empresa,Empresa empresa2){
		try{
			return this.seCumpleLaCondicion(empresa, empresa2);
		}
		catch(IdentificadorInexistente oo)
		{
			return false;
		}
	}
	public abstract boolean seCumpleLaCondicion(Empresa empresa, Empresa empresa2);
	
	@Override
	public String toString(){
		return nombre;
	}
}
