package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Condicion;

public class RepositorioDeCondiciones extends Repositorio {

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

	public void agregarCondicion(Condicion condicionAAgregar) {
		this.addInstanceToDB(Condicion.class, condicionAAgregar);
	}
	
	public List<Condicion> mostrarListaDeCondiciones() {
		return this.getFromDB(Condicion.class);
	}
	public boolean existe(String nombre) {
		return this.existe(nombre, Condicion.class);
	}
	public void borrar(String nombre) {
		this.borrar(nombre, Condicion.class);
	}

}
