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
import model.repositorios.RepositorioDeEmpresasInterfaz;
import model.repositorios.RepositorioDeIndicadores;

public class LeerEmpresasProgramado extends TimerTask{

private String path;
private Date ultimaModificacion;

public LeerEmpresasProgramado(String path) {
	super();
	this.path = path;
	ultimaModificacion = null;
}

public void run(){
	this.run(new RepositorioDeEmpresas());
}
public void run(RepositorioDeEmpresasInterfaz repo) {
	
	System.out.println("CARGANDO EMPRESAS...");
	
	if(IOs.fueModificadoDesdeLaUltimaLectura(path,ultimaModificacion)) {
		try {
				List<Empresa> listaDeEmpresas = IOs.leerArchivo(path);
				listaDeEmpresas.forEach(x->this.chequearEmpresa(x,repo));
			
		}
		catch (Exception e) {
			e.printStackTrace();
	    }
	}
	else {
		System.out.println("el archivo no fue modificado"); // esto es para guiarme
	}
}

private void chequearEmpresa(Empresa empresa,RepositorioDeEmpresasInterfaz repo) {
	Empresa empresaDeDB = repo.buscar(empresa.getNombre());
	if(empresaDeDB == null) {
		repo.agregarEmpresas(empresa);
	}
	else {
		empresa.getBalances().forEach(balance -> this.actualizarBalance(balance,empresaDeDB));
		//Agregar balances que no esten en la empresa de la bd.
	}
}

private void actualizarBalance(Balance balance, Empresa empresaDeLaDB) {
	
	System.out.println(balance.toString());
	
	if(empresaDeLaDB.getBalances().stream().anyMatch( b-> b.equals(balance))) {
		Balance balanceViejo = empresaDeLaDB.getBalances().stream().filter( b-> b.equals(balance)).findFirst().get();
		List<Cuenta> cuentas = balanceViejo.getCuentas();
		cuentas.forEach(cuenta -> cuenta.actualizarCuenta(balanceViejo,balance));
	}
	else {
		empresaDeLaDB.agregarBalance(balance);
	}
	
	
	
	
	
}


}
