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
import repositorios.RepositorioDeIndicadores;


public class IOs {
	
	public static void cargarIndicador(Indicador indicador, String path) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
			writer.write("\n" + indicador.mostrarFormula() + ";");
			writer.newLine();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			throw new RuntimeException("Error al cargar el indicador");
		}
	}

	
	public static void leerIndicadoresDeArchivo(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
		    String line;

		    while ((line = br.readLine()) != null) {
		    	
				RepositorioDeIndicadores.agregarIndicador(new Indicador(line));
		    }
		    br.close();
		} catch (IOException e) {
				e.printStackTrace();
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
