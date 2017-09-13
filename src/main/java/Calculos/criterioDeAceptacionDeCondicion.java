package Calculos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class criterioDeAceptacionDeCondicion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public criterioDeAceptacionDeCondicion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public abstract boolean cumpleCriterioDeAceptacionDeCondicion(double valorMinimo, double valorActual);
}
