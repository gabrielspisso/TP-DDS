package model.condicionesYMetodologias;

public class Resultado {

	private String nombreEmpresa;
	double valor;

	
	public Resultado(String nombreEmpresa, int valor) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.valor = ((double)valor)/100;
	}
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public double getValor() {
		return valor;
	}
	
}
