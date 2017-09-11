package condicionesYMetodologias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import model.Empresa;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Condicion {
	
	/*@Id
	@GeneratedValue
	private Long id;
	*/
	
	public boolean cumpleCondicion(Empresa empresa, Empresa empresa1){
		return false;
	}
}
