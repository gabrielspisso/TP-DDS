package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesYMetodologias.Condicion;
import model.Empresa;

public class RepositorioDeEmpresas {
	//private static List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();

	/*
	 * public static void agregarEmpresas(List<Empresa> listaDeEmpresas2){
	 * 
	 * listaDeEmpresas.removeIf(x-> listaDeEmpresas2.contains(x));
	 * listaDeEmpresas.addAll(listaDeEmpresas2); }
	 */

	/*
	 * public static List<Empresa> mostrarEmpresas() { List<Empresa>
	 * listaDeEmpresas2 = new ArrayList<Empresa>();
	 * listaDeEmpresas2.addAll(listaDeEmpresas); return listaDeEmpresas2; }
	 */

	public static void agregarEmpresas(Empresa empresaAAgregar) {
		Repositorio.addInstanceToDB(Empresa.class, empresaAAgregar, "Empresa");
	}

	// entityManager().createQuery("from Consultora c where c.nombre like :nombre",
	// Consultora.class).setParameter("nombre", "%" + nombre + "%").getResultList();

	public static List<Empresa> traerEmpresasDeLaDB() {
		return Repositorio.getFromDB(Empresa.class, "Empresa");
	}

}
