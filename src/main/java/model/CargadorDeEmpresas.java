package model;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import repositorios.RepositorioDeEmpresas;


public class CargadorDeEmpresas {
	
	public static List<Empresa> obtenerCuentasEmpresas(String ruta){

			return IOs.leerArchivo(ruta);
	}
	
	public static void traerEmpresasDeLaDB() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		List <Empresa> le = em.createQuery("from Empresa", Empresa.class).getResultList();
		le.forEach(e -> RepositorioDeEmpresas.agregarEmpresas(e));
	}

}
