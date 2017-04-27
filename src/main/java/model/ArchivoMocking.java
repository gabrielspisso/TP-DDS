package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ArchivoMocking {
	private static List<Empresa> listaHarcodeada(){
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("burro",1),new Cuenta("probando",2));
		List<Cuenta> listaDeCuentas2 = Arrays.asList(new Cuenta("hola",1));
		
		List<Balance> listaDeBalances = Arrays.asList(new Balance("Primer Semestre",listaDeCuentas),new Balance("Segundo Semestre",listaDeCuentas2));
		List<Cuenta> listaDeCuentas3 = Arrays.asList(new Cuenta("pato",3));
		
		List<Balance> listaDeBalances2 = Arrays.asList(new Balance("Anual",listaDeCuentas3));
		List<Empresa> x = Arrays.asList(new Empresa (listaDeBalances),new Empresa(listaDeBalances2));
		return x;
	}
	
	public static void escribirEnArchivo(String path){
		String json = new Gson().toJson(listaHarcodeada());
		try {
			FileWriter file = new FileWriter(path);
			System.out.println(json);
			file.write(json);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String readFileAsString(String fileName){
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
			e.printStackTrace();
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
