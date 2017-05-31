package model;

import model.Indicador;
import repositorios.RepositorioDeIndicadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class IOIndicadores {
	
	public static void leerArchivo(){
		//List<Indicador> listaIndicadores = new ArrayList<Indicador>();	
		try {
			FileInputStream in = new FileInputStream("archivoIndicadores.txt");
			JsonReader jReader = new JsonReader(new InputStreamReader(in));
			jReader.beginArray();
			while(jReader.hasNext()) {
				
				Indicador indicador = new Gson().fromJson(jReader, Indicador.class);
				RepositorioDeIndicadores.agregarIndicador(indicador);
				//listaIndicadores.add(indicador);
				System.out.println(indicador);
			}
			jReader.endArray();
			jReader.close();
			in.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return listaIndicadores;
	}
	public static void escribirArchivo(List<Indicador> listaIndicadores){
		try {
			FileOutputStream arch = new FileOutputStream("archivoIndicadores.txt");
			JsonWriter jWriter = new JsonWriter(new OutputStreamWriter(arch));
			jWriter.setIndent("  ");
			jWriter.beginArray();
			Gson gson = new Gson();
			for(Indicador indicador : listaIndicadores){
				gson.toJson(indicador, Indicador.class, jWriter);
			}
			
			jWriter.endArray();
			jWriter.close();
			arch.close();
		}
		catch (IOException e){
			//no sabria que poner
		}
	}
	
	/*public static String leerPorBuffer(){
		StringBuilder strBuilder = new StringBuilder();
		String line;
		try {
			FileReader arch = new FileReader("archivoIndicadores.txt");
			BufferedReader buffReader = new BufferedReader(arch);
			while((line = buffReader.readLine()) != null){
				strBuilder.append(line);
				line = buffReader.readLine();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strBuilder.toString();
	}*/
	
	/*public static Indicador leerArchivoIndicadores() {
		String gson = leerPorBuffer();
		Indicador indicador = new Gson().fromJson(gson, Indicador.class);
		RepositorioDeIndicadores.agregarIndicador(indicador);
		return indicador;
	}*/

}