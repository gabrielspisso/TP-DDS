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
		Usuario usuario = new Usuario("GabrielSpisso","123");
		RepositorioDeUsuario.agregarUsuario(usuario);
		Usuario usuario2 = new Usuario("GabrielMaiori","123");
		RepositorioDeUsuario.agregarUsuario(usuario2);
		
		Usuario usuario3 = new Usuario("SantiagoParedes","123");
		RepositorioDeUsuario.agregarUsuario(usuario3);
		
		CargadorDeEmpresas.obtenerCuentasEmpresasHardcodeada().forEach(emp-> RepositorioDeEmpresas.agregarEmpresas(emp));
		
		IOs.listaDeIndicadoresMockeada().forEach(ind-> RepositorioDeIndicadores.agregarIndicador(ind));
		
		
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		CondicionConAño test = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		CondicionConAño test2 = new CondicionConAño(indicador,Menor.getSingletonMenor(),8,1,"");
		Metodologia metodologiaAimplementarDeSpisso = new Metodologia("Metodologia DDS",null,Arrays.asList(test, test2),usuario );
		
		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeSpisso);
		Metodologia metodologiaAimplementarDeMaiori = new Metodologia("Metodologia DDS",null,Arrays.asList(test, test2),usuario2 );

		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeMaiori);
		
		CondicionEntreDosEmpresas test3 = new CondicionEntreDosEmpresas(indicador,Menor.getSingletonMenor(),"Mayor indicador1" );
		condicionConCalculo test4 = new condicionConCalculo(indicador,Menor.getSingletonMenor(),Promedio.getSingletonPromedio(),3,"Promedio de indicador 1");
		
		Metodologia metodologiaAimplementarDeSpisso2 = new Metodologia("Metodologia PDEP",null,Arrays.asList(test, test2,test3,test4),usuario );
		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeSpisso2);
	}
}
