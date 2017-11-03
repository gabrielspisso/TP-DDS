package model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.repositorios.RepositorioDeEmpresas;


public class CargadorDeEmpresas {
	public static List<Empresa> obtenerCuentasEmpresas(String ruta){
			return IOs.leerArchivo(ruta);
	}
}
