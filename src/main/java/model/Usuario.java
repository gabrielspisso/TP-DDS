package model;

public class Usuario {
	private String nombre;
	private String mail;
	private String password;
	
	private String frase;

	Usuario(String nombre, String mail, String password, String frase) {
		super();
		this.nombre = nombre;
		this.mail = mail;
		this.password = password;
		this.frase = frase;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}
	
	
}
