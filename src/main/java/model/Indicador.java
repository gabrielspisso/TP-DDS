package model;
import java.util.List;




public class Indicador {
	private String nombre;
	private List<Termino> terminos;
	
	@Override
	public String toString(){
		return nombre;
	}
	

	public Indicador(String nombre, List<Termino> listaDeTerminos) {
		this.nombre = nombre;
		this.terminos = listaDeTerminos;
	}



	public double calcularValor(List<Cuenta> listaDeCuentas, List<Indicador> listaDeIndicadores) {
		return terminos.stream().
				mapToDouble(
					termino -> termino.calcular(listaDeCuentas, listaDeIndicadores))
				.sum();
	}

	public String mostrarFormula() {
		return "";
	}


	public String getNombre() {
		return nombre;
	}


	public boolean contieneEsteToken(String token) {
		return terminos.stream().anyMatch(termino -> termino.contieneEsteToken(token));
	}
	
	

}
