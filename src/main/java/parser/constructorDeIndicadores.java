package parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Cuenta;
import repositorios.RepositorioDeIndicadores;

public class constructorDeIndicadores {

	static List<TokenYTipo> listaDeTokens;
	static List<Indicador> listaDeIndicadores = new ArrayList<>();
	static String ruta = "C:\u005c\u005cUsers\u005c\u005cGabriel\u005c\u005cDesktop\u005c\u005cprueba.txt";
	public void leerIndicadores(){
		//listaDeTokens = test.obtenerTokens(ruta);
		
		System.out.println("hola");
		/*
		tipo: identificador valor: var
		tipo: asignacion valor: =
		tipo: numero valor: 3
		tipo: findelinea valor: ;*/
		
	}
	
	public static Indicador algo(){
		//listaDeTokens = test.obtenerTokens(ruta);
		String nombre = listaDeTokens.get(0).getValor();
		listaDeTokens.remove(0);
		listaDeTokens.remove(0);
		
		listaDeTokens.remove(listaDeTokens.size() - 1);
		
		
		return new Indicador(listaDeTokens, nombre);
		//listaDeTokens;
	}
	
	
	
	public static void main(String[] args){
		//Indicador var = algo();
		
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",7),new Cuenta("FREECASHFLOW",2));
		/*
		List<TokenYTipo> lista = Arrays.asList(new TokenYTipo("Identificador", "hola"),
		                                        new TokenYTipo("Operador", "+"),
		                                        new TokenYTipo("NUMERO", "2")
		                                        );*/
		
		//listaDeIndicadores.add(var);

		//System.out.println(var.calcular(0, listaDeCuentas, listaDeIndicadores));
		
		String expresion = "Hola = 2 * 2 ;";
		
		try{
			SCANNER.analizarLinea(expresion);
			List<TokenYTipo> lista = SCANNER.obtenerTokens();
			String nombre = lista.get(0).getValor();
			
			List<TokenYTipo> lista2 = lista.subList(2, lista.size());
			
			TokenYTipo a = lista2.get(0);
			
			Indicador nuevoIndicador = new Indicador(lista2, nombre);
			RepositorioDeIndicadores.agregarIndicador(nuevoIndicador);
			System.out.println(nuevoIndicador.calcularValor(listaDeCuentas, null));
		}
		catch(RuntimeException ex){
			System.out.println(ex.getMessage());
			//System.out.println("Ingresaste mal, boludo");
		}
		//System.out.println(test.obtenerTokens(expresion).get(2).getValor());
		//test.obtenerTokens(expresion);
		/*
		 try
         {
			 test analizador = new test(stream);
             analizador.Programa();
             System.out.println("Se compilo con exito");
             analizador.ReInit(stream);
             //test analizador2 = new test(stream);
             analizador.Programa();
             System.out.println("Se compilo con exito");
         }
         catch(ParseException ex)
         {
                 System.out.println(ex.getMessage());
                 System.out.println("Se han encontrado errores");
         }*/
		 
		
		//test.obtenerTokens(expresion);
		
		//test.ReInit(stream);
		
		//test.obtenerTokens2(expresion);
		
		//Indicador var2 = new Indicador(lista, "PUtoelquelee");
		
		//System.out.println(var2.calcular(0, listaDeCuentas, listaDeIndicadores));
	}
	//new Indicador(List<Indicador> listaDeIndicadores, StringBuilder nombre)
	
}
