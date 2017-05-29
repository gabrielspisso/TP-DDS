package parser;

public class TokenYTipo {
	private String tipo;
	private String valor;
	
	public String getTipo() {
		return tipo;
	}

	public String getValor() {
		return valor;
	}

	public TokenYTipo(String t, StringBuilder v) {
		tipo = t;
		valor = v.toString();
	}


	public double calcular(){
		return 0;
	}

	
}
