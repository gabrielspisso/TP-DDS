package model;

import java.util.List;


public class CargadorDeEmpresas {
	
	public static List<Empresa> obtenerCuentasEmpresas(){
		return ArchivoMocking.leerArchivo("/Users/Gabriel/Documents/archivoEmpresas.txt");
	}
	
}
