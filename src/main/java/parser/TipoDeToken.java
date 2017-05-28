package parser;

public class TipoDeToken {
	private String tipo;
	private String valor;
	
	public String getTipo() {
		return tipo;
	}

	public String getValor() {
		return valor;
	}

	public TipoDeToken(String t, StringBuilder v) {
		tipo = t;
		valor = v.toString();
	}
	
}
