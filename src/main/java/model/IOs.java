package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Builders.IndicadorBuilder;
import model.Excepciones.RecursiveException;
import model.condicionesYMetodologias.Metodologia;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeMetodologias;


public class IOs {
	
	private static void escribirEnArchivo(Indicador indicador,String path){

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
			writer.write(indicador.getNombre() + "=" +indicador.mostrarFormula() + ";");
			writer.newLine();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			throw new RuntimeException("Error al cargar el indicador");
		}
	}
	
	
	private static void vaciarArchivo(String path){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write("");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void cargarIndicador(Indicador indicador, String path) {
		vaciarArchivo(path);
		//RepositorioDeIndicadores.traerIndicadoresDeLaDB().forEach(x->escribirEnArchivo(x,path));
	}

	
	public static void leerIndicadoresDeArchivo(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
		    while ((line = br.readLine()) != null) {
		    	Indicador indicador = IndicadorBuilder.Build(line);
				RepositorioDeIndicadores.agregarIndicador(indicador);
		    }
		    br.close();
		} catch (IOException e) {
				e.printStackTrace();
		}
		catch(RecursiveException ex){
			//Es por un tema de que el build tira una excepcion si le mandas algo recursivo, pero si ya estaba creado, te deberia dejar crearlo pero no ver su valor!
			//Otra opcion seria que no te deje leerlo si son recursivos
		}

	}
	
	public static String readFileAsString(String fileName){
	    StringBuilder salida = new StringBuilder();
	    String line;
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(fileName));
	        line = br.readLine();
	        while (line != null) {
	        	salida.append(line);
	            line = br.readLine();
	        }
	        br.close();
	    }
	    catch (IOException e) {
			throw new RuntimeException("Archivo no encontrado!");
	    	//e.printStackTrace();
		}
	    return salida.toString();
	}
	
	public static List<Empresa> leerArchivo(String ruta){
		String json = readFileAsString(ruta);
		Type listType = new TypeToken<List<Empresa>>(){}.getType();
			
		List<Empresa> listaDeEmpresas = new Gson().fromJson(json, listType); 
	
		return listaDeEmpresas;
	}
	
	
	private static void escribirMetodologia(Metodologia metodologia, String path) {
		String metod = convertirMetodologiaEnJson(metodologia);
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(path, true));
			wr.write(metod);
			wr.newLine();
			wr.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void guardarMetodologias(Metodologia metodologia, String path) {
		vaciarArchivo(path);
		RepositorioDeMetodologias.traerMetodologiasDeLaDB().forEach(m -> escribirMetodologia(m, path));
	}
	//El escribir no va a andar con el leer, porque escribis de a metodologias, si escribis todas juntas anda.
	private static String convertirMetodologiaEnJson(Metodologia metodologia) {
		Gson gson = new Gson();
		String json = gson.toJson(metodologia);
		return json;
	}
	
	public static List<Metodologia> leerArchivoDeMetodologias(String ruta){
		String json = IOs.readFileAsString(ruta);
		Type listType = new TypeToken<List<Metodologia>>(){}.getType();
		List<Metodologia> ListaDeMetodologias = new Gson().fromJson(json, listType); 
		return ListaDeMetodologias;
	}
	
	
		public static List<Indicador> listaDeIndicadoresMockeada(){
		
		Indicador indicador1 = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");

		Indicador indicador2 = IndicadorBuilder.Build("cc=FDS+10;");

		Indicador indicador3 = IndicadorBuilder.Build("b=2+2;");

		Indicador indicador4 = IndicadorBuilder.Build("ro=6;");

		Indicador indicador5 = IndicadorBuilder.Build("barby=42;");

		Indicador indicador6 = IndicadorBuilder.Build("super=barby;");

		Indicador indicador7 = IndicadorBuilder.Build("I1=1;");

		Indicador indicador8 = IndicadorBuilder.Build("I2=I1+1;");

		Indicador indicador9 = IndicadorBuilder.Build("L3=L4;");

		Indicador indicador10 = IndicadorBuilder.Build("HOLA=798+indicador1;");

		return Arrays.asList(indicador1,indicador2,indicador3,indicador4,indicador5,indicador6,indicador7,indicador8,indicador9,indicador10);

	}


		public static boolean fueModificadoDesdeLaUltimaLectura(String path, Date ultimaModificacion) {
			if(ultimaModificacion == null) {
				return true;
			}
			File file = new File(path);
			Date lastModified = new Date(file.lastModified()); 
			return lastModified.after(ultimaModificacion);
		}
	
	
	
	
	/*
	private static void escribirEnArchivoCondicion(Condicion condicion,String path){

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
			writer.write(condicion.getNombre() + "=" +condicion.mostrarFormula() + ";");
			writer.newLine();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			throw new RuntimeException("Error al cargar la condicion");
		}
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
