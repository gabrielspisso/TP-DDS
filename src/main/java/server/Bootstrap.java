package server;

import java.math.BigDecimal;
import java.util.Arrays;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.CargadorDeEmpresas;
import model.Empresa;
import model.IOs;
import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Calculos.Mayor;
import model.Calculos.Menor;
import model.Calculos.Promedio;
import model.condicionesYMetodologias.CondicionConAño;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.Metodologia;
import model.condicionesYMetodologias.condicionConCalculo;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeEmpresas;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeMetodologias;
import model.repositorios.RepositorioDeUsuario;



public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init(){
			Usuario usuario = new Usuario("GabrielSpisso","123");
		RepositorioDeUsuario.agregarUsuario(usuario);
		Usuario usuario2 = new Usuario("GabrielMaiori","123");
		RepositorioDeUsuario.agregarUsuario(usuario2);
		
		Usuario usuario3 = new Usuario("SantiagoParedes","123");
		RepositorioDeUsuario.agregarUsuario(usuario3);
		
		Usuario usuario4 = new Usuario("TomasMoreira","123");
		RepositorioDeUsuario.agregarUsuario(usuario4);
		
		Usuario usuario5 = new Usuario("Roli","123");
		RepositorioDeUsuario.agregarUsuario(usuario5);
		
		CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().forEach(emp-> RepositorioDeEmpresas.agregarEmpresas(emp));
		
		IOs.listaDeIndicadoresMockeada().forEach(ind-> RepositorioDeIndicadores.agregarIndicador(ind));
		
		
		Indicador indicador = IOs.listaDeIndicadoresMockeada().get(0);
		CondicionConAño test = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		CondicionConAño test2 = new CondicionConAño(indicador,Menor.getSingletonMenor(),8,1,"");
		Metodologia metodologiaAimplementarDeSpisso = new Metodologia("Metodologia DDS",null,Arrays.asList(test, test2),null,usuario );
		
		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeSpisso);
		Metodologia metodologiaAimplementarDeMaiori = new Metodologia("Metodologia DDS",null,Arrays.asList(test, test2),null,usuario2 );

		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeMaiori);
		
		CondicionEntreDosEmpresas test3 = new CondicionEntreDosEmpresas(indicador,Menor.getSingletonMenor(),"Mayor indicador1" );
		condicionConCalculo test4 = new condicionConCalculo(indicador,Menor.getSingletonMenor(),Promedio.getSingletonPromedio(),3,"Promedio de indicador 1");
		
		Metodologia metodologiaAimplementarDeSpisso2 = new Metodologia("Metodologia PDEP",null,Arrays.asList(test, test2,test4),Arrays.asList(test3),usuario );
		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeSpisso2);
	}
}
