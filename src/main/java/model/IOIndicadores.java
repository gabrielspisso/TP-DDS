package model;

import model.Indicador;
import repositorios.RepositorioDeIndicadores;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class IOIndicadores {
	
	static boolean append = true; //<-- se que queda feo pero a las 3 AM es lo que se me ocurre por ahora
	public static void escribirArchivo(Indicador indicador){
		boolean append = true;
		String json = new Gson().toJson(indicador);
		try {
			FileWriter arch = new FileWriter("archivoIndicadores.txt", append);
			arch.write(json);
			arch.close();
		}
		catch (IOException e){
			//no sabria que poner
		}
	}
	
	public static String leerPorBuffer(){
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
	}
	
	public static void leerArchivo() {
		String gson = leerPorBuffer();
		Indicador indicador = new Gson().fromJson(gson, Indicador.class);
		
		RepositorioDeIndicadores.agregarIndicador(indicador);
	}

}