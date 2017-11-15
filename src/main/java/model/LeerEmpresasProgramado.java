package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.stream.Stream;

import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeIndicadores;

public class LeerEmpresasProgramado extends TimerTask{

private String path;
private Date ultimaModificacion;

public LeerEmpresasProgramado(String path) {
	super();
	this.path = path;
	ultimaModificacion = null;
}

@Override
public void run() {
	
	if(IOs.fueModificadoDesdeLaUltimaLectura(path,ultimaModificacion)) {
		try {
				List<Empresa> listaDeEmpresas = IOs.leerArchivo(path);
				listaDeEmpresas.forEach(x->this.chequearEmpresa(x));
			
		}
		catch (Exception e) {
			e.printStackTrace();
	    }
	}
	else {
		System.out.println("el archivo no fue modificado"); // esto es para guiarme
	}
}

private void chequearEmpresa(Empresa empresa) {
	Empresa empresaDeDB = Repositorio.buscar(empresa.getNombre(), Empresa.class);
	if(empresaDeDB == null) {
		RepositorioDeEmpresas.agregarEmpresas(empresa);
	}
	else {
		empresaDeDB.getBalances().forEach(balance -> this.actualizarBalance(balance,empresa));
		//Agregar balances que no esten en la empresa de la bd.
	}
}

private void actualizarBalance(Balance balance, Empresa empresaDelArchivo) {
	
	System.out.println(balance.toString());
	
	if(empresaDelArchivo.getBalances().stream().anyMatch( b-> b.equals(balance))) {
		Balance balanceNuevo = empresaDelArchivo.getBalances().stream().filter( b-> b.equals(balance)).findFirst().get();
		List<Cuenta> cuentas = balance.getCuentas();
		cuentas.forEach(cuenta -> cuenta.actualizarCuenta(balance,balanceNuevo));
	}
	
	
	
	
	
}


}
