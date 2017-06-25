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
					termino -> termino.calcularValor(listaDeCuentas, listaDeIndicadores))
				.sum();
	}

	//SI ALGUIEN LO VE, ARREGLE ESTA ASQUEROSIDAD
	public String mostrarFormula() {
		String formula = terminos.get(0).mostrarFormulaSinSigno();//Agarro la formula sin signo porque el parser no lo soporta en el primer termino
		for (int i = 1; i < terminos.size(); i++) {
			formula += " " + terminos.get(i).mostrarFormula();
		}
		return formula;
	}

	public String mostrarFormulaCompleta(){
		return nombre + " = " + mostrarFormula();
	}

	public String getNombre() {
		return nombre;
	}


	public boolean contieneEsteToken(String token) {
		return terminos.stream().anyMatch(termino -> termino.contieneEsteToken(token));
	}
	
	

}
