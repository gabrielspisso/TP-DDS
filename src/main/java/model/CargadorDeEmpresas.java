package model;

import java.util.List;


public class CargadorDeEmpresas {
	
	public static List<Empresa> obtenerCuentasEmpresas(String ruta){

			return ArchivoMocking.leerArchivo(ruta);
	}

}
