package model.repositorios;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.beanutils.converters.FileConverter;

public class RepositorioDeArchivosMockeado implements RepositorioDeArchivosInterfaz {

	@Override
	public File descargarArchivo(String nombreArchivo) {
		
		return new File("archivoEmpresas.txt");
	}

}
