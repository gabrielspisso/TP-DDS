package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Condicion;

public class RepositorioDeCondiciones {

	/*public static void agregarCondicion(Condicion condicionAAgregar) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();

		List<Condicion> listaDeCondiciones = em
				.createQuery("from Condicion e where e.nombre like :nombre", Condicion.class)
				.setParameter("nombre", condicionAAgregar.toString()).getResultList();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (listaDeCondiciones.isEmpty())

			em.persist(condicionAAgregar);
		else {
			System.out.println("esta parte no esta hecha, habria que modificar la empresa, o dejarla como esta");
			// Empresa empresa1 = listaDeEmpresas.get(0);
			// em.remove(empresa1);
			// empresa1.setBalances(empresaAAgregar.getBalances());
			// em.persist(empresaAAgregar);
		}

		tx.commit();
	}*/

	public static void agregarCondicion(Condicion condicionAAgregar) {
		Repositorio.addInstanceToDB(Condicion.class, condicionAAgregar);
	}
	
	public static List<Condicion> mostrarListaDeCondiciones() {
		return Repositorio.getFromDB(Condicion.class);
	}
	public static boolean existe(String nombre) {
		return Repositorio.existe(nombre, Condicion.class);
	}
	public static void borrar(String nombre) {
		Repositorio.borrar(nombre, Condicion.class);
	}

}
