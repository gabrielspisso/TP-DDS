package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.uqbar.commons.utils.Observable;

@Entity
@Table(name = "Usuario",uniqueConstraints= @UniqueConstraint(columnNames={"nombre"}))
public class Usuario {
	
	private String password;
	private String nombre;
	@Id
	@GeneratedValue
	private Long id;

	protected Usuario() {
		
	}
	public Usuario(String nombre, String password) {
		super();
		this.nombre = nombre;

		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}
