package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Excepciones.RecursiveException;
import model.Builders.IndicadorBuilder;
import repositorios.RepositorioDeIndicadores;


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
		RepositorioDeIndicadores.getListaDeIndicadores().forEach(x->escribirEnArchivo(x,path));
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
}
