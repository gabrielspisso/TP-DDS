package clasesResultantes;

public class ResultadoMetodologia {

	private String nombreEmpresa;
	double valor;

	
	public ResultadoMetodologia(String nombreEmpresa, int valor) {
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
