package model;

import java.util.Arrays;
import java.util.List;

public class CargadorDeEmpresas {
	
	static public List<Empresa> obtenerCuentasEmpresas(){
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("burro",1),new Cuenta("anda hijo de mil puta",2));
		List<Cuenta> listaDeCuentas2 = Arrays.asList(new Cuenta("anda hijo de mil puta",1));
		List<Balance> listaDeBalances = Arrays.asList(new Balance("Primer Semestre",listaDeCuentas),new Balance("Segundo Semestre",listaDeCuentas2));
		List<Cuenta> listaDeCuentas3 = Arrays.asList(new Cuenta("anda hijo de mil puta",3));
		
		List<Balance> listaDeBalances2 = Arrays.asList(new Balance("Anual",listaDeCuentas3));
		List<Empresa> x = Arrays.asList(new Empresa (listaDeBalances),new Empresa(listaDeBalances2));
		return x;
	}
	
}
