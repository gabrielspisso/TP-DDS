package server;

import java.math.BigDecimal;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.CargadorDeEmpresas;
import model.Empresa;
import model.Usuario;
import model.condicionesYMetodologias.Metodologia;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeUsuario;



public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init(){
		/*
		Usuario usuario = new Usuario("GabrielSpisso","123");
		RepositorioDeUsuario.agregarUsuario(usuario);
		Usuario usuario2 = new Usuario("GabrielMaiori","123");
		RepositorioDeUsuario.agregarUsuario(usuario2);
		
		Usuario usuario3 = new Usuario("SantiagoParedes","123");
		RepositorioDeUsuario.agregarUsuario(usuario3);
		
		CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().forEach(emp-> RepositorioDeEmpresas.agregarEmpresas(emp));
		*/
	}
}
