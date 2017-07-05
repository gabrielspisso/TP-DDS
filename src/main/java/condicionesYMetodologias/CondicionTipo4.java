package condicionesYMetodologias;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import Calculos.Calculo;
import model.Balance;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeIndicadores;

public class CondicionTipo4 {

	Indicador indicador;
	String comportamiento;
	public CondicionTipo4(String comportamiento, Indicador indicador){
		this.indicador = indicador;
		this.comportamiento = comportamiento;
	}
	public boolean cumpleCondicion(Empresa empresa){
		Comparator<Balance> comparadorTurbio = Comparator.comparing(
				X ->indicador.calcularValor(X.getCuentas())
		);
		
		//Esto no anda
		return empresa.getBalances().stream().equals(empresa.getBalances().stream().sorted(comparadorTurbio));
	}
}
