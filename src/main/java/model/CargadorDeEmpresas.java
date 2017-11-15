package model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.repositorios.RepositorioDeEmpresas;


public class CargadorDeEmpresas {
	
	public static List<Empresa> obtenerCuentasEmpresas(String ruta){

			return IOs.leerArchivo(ruta);
	}
	
	public static List<Empresa> obtenerCuentasEmpresasHardcodeada(){
		Cuenta CuentaFDSFacebook = new Cuenta("FDS", 0);
		Cuenta CuentaFCFFacebook = new Cuenta("FREE CASH FLOW", 2);
		
		
		Balance BalanceFacebook1 = new Balance(2017, "Primer Semestre",Arrays.asList(CuentaFDSFacebook,CuentaFCFFacebook));
		
		
		
		Balance BalanceFacebook2 =  new Balance(2017, "Segundo Semestre",Arrays.asList(new Cuenta("FDS", 1)));
		Empresa facebook = new Empresa("Facebook",Arrays.asList(BalanceFacebook1,BalanceFacebook2));
		
		
		Balance BalanceTwitter = new Balance(2017,"Anual",Arrays.asList(new Cuenta("FREE CASH FLOW",3)));
		
		Empresa twitter = new Empresa("Twitter",Arrays.asList(BalanceTwitter));
		
		
		Balance balanceInstagram = new Balance(2016,"Segundo Semestre",Arrays.asList(new Cuenta("EBITDA",1),new Cuenta("INGRESO NETO DISCONTINUO",25000),new Cuenta("FREE CASH FLOW",5000)));
		Empresa instagram = new Empresa("Instagram",Arrays.asList(balanceInstagram));
		
		
		return Arrays.asList(facebook,twitter,instagram);
		
	}
}
