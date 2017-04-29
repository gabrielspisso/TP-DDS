package model;

import java.util.List;


public class CargadorDeEmpresas {
	
	public static List<Empresa> obtenerCuentasEmpresas(){
		return ArchivoMocking.leerArchivo("ArchivoEmpresas.txt");
	}
	
}
