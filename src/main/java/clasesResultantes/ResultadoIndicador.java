package clasesResultantes;

public class ResultadoIndicador {

	int anio;
	public int getAnio() {
		return anio;
	}
	public String getPeriodo() {
		return periodo;
	}
	public String getResultado() {
		return resultado;
	}
	public ResultadoIndicador(int anio, String periodo, String resultado) {
		super();
		this.anio = anio;
		this.periodo = periodo;
		this.resultado = resultado;
	}
	String periodo;
	String resultado;
}
