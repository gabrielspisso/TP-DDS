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
		List<Cuenta> listaDeCuentas = Arrays.asList(new Cuenta("FDS",1),new Cuenta("FREE CASH FLOW",2));
		List<Cuenta> listaDeCuentas2 = Arrays.asList(new Cuenta("EBITDA",1));
		List<Cuenta> listaDeCuentas3 = Arrays.asList(new Cuenta("FREE CASH FLOW",3));
		List<Cuenta> listaDeCuentas4 = Arrays.asList(new Cuenta("EBITDA",1),new Cuenta("INGRESO NETO DISCONTINUO",25000),new Cuenta("FDS",5000));
		
		List<Balance> listaDeBalances = Arrays.asList(new Balance("2017","Primer Semestre",listaDeCuentas),new Balance("2017","Segundo Semestre",listaDeCuentas2));
		List<Balance> listaDeBalances2 = Arrays.asList(new Balance("2016","Anual",listaDeCuentas3));
		List<Balance> listaDeBalances3 = Arrays.asList(new Balance("2016","Segundo Semestre",listaDeCuentas4));
		
		List<Empresa> x = Arrays.asList(new Empresa ("Facebook",listaDeBalances),new Empresa("Twitter",listaDeBalances2),new Empresa("Instagram",listaDeBalances3));
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
