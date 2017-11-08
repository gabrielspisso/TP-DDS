package server;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import mockers.CargadorDeEmpresasMock;
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
import model.condicionesYMetodologias.CondicionConComportamiento;
import model.condicionesYMetodologias.CondicionEntreDosEmpresas;
import model.condicionesYMetodologias.CondicionesFiltro;
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
		
		CargadorDeEmpresasMock.obtenerCuentasEmpresas().forEach(emp-> RepositorioDeEmpresas.agregarEmpresas(emp));
		
		IOs.listaDeIndicadoresMockeada().forEach(ind-> RepositorioDeIndicadores.agregarIndicador(ind));
		
		Indicador indicador = IndicadorBuilder.Build("indicador53=FREE CASH FLOW+4;");
		CondicionConAño test = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		CondicionConAño test2 = new CondicionConAño(indicador,Menor.getSingletonMenor(),8,1,"");
		
		Metodologia metodologiaAimplementarDeSpisso = new Metodologia("Metodologia DDS",null,Arrays.asList(test, test2), Arrays.asList(),usuario );
		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeSpisso);
		Metodologia metodologiaAimplementarDeMaiori = new Metodologia("Metodologia DDS",null,Arrays.asList(test, test2), Arrays.asList(),usuario2 );
		RepositorioDeMetodologias.agregarMetodologia(metodologiaAimplementarDeMaiori);
		CondicionEntreDosEmpresas test3 = new CondicionEntreDosEmpresas(indicador,Menor.getSingletonMenor(),"Mayor indicador1" );
		
		
		Indicador indicadorEasy = IndicadorBuilder.Build("ccA=10;");
		CondicionConComportamiento testEasy = new CondicionConComportamiento(indicador,Mayor.getSingletonMayor(),2,"");
		CondicionEntreDosEmpresas entreEmpresas = new CondicionEntreDosEmpresas(indicador,Mayor.getSingletonMayor(),"nnombre");
		CondicionConAño testanio = new CondicionConAño(indicador,Mayor.getSingletonMayor(),8,1,"");
		Metodologia meto = new Metodologia("Metodologia TOTAL", null, Arrays.asList(testEasy, testanio), Arrays.asList(entreEmpresas), usuario2);
			
		condicionConCalculo test4 = new condicionConCalculo(indicador,Menor.getSingletonMenor(),Promedio.getSingletonPromedio(),3,"Promedio de indicador 1");
		Metodologia metodologiaAimplementarDeSpisso2 = new Metodologia("Metodologia PDEP",null, Arrays.asList(test,test2,test4), Arrays.asList(test3),usuario );

		RepositorioDeMetodologias.agregarMetodologia(meto);
		
		System.out.println("ASDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDKDAKSAJKADSJKKASncNKCAKNcaCALMACSLMACLACSMLASCLMASLMASCLM/nsdljsfjsajlfjlsljkfsadjkfdsadf\nsafasdfasdfasdfasdfasd/\nsadfsadfasdfasdf/\ndfasdfasdfas/\nsdfasdfsd");
	
		for(int i=0; i < RepositorioDeMetodologias.traerMetodologiasDeLaDB().size() ; i++ )	{
			System.out.println(RepositorioDeMetodologias.traerMetodologiasDeLaDB().get(i).getNombre());
			}
	
	}
}
