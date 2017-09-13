package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesYMetodologias.Condicion;
import model.Empresa;

public class RepositorioDeCondiciones {
	//private static List<Condicion> listaDeCondiciones = new ArrayList<Condicion>();

	/*public static void agregarCondicion(List<Condicion> listaDeCondiciones2) {
		listaDeCondiciones.removeIf(x -> listaDeCondiciones2.contains(x));
		listaDeCondiciones.addAll(listaDeCondiciones2);
	}*/

	/*public static List<Condicion> mostrarListaDeCondiciones() {

		return entityManager().createQuery("from Condicion", Condicion.class) //
				.getResultList();
		;
	}*/

	public static void agregarCondicion(Condicion condicionAAgregar) {
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
	}

	public static List<Condicion> mostrarListaDeCondiciones() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return em.createQuery("from Condicion", Condicion.class).getResultList();
	}

	/*public static List<Condicion> mostrarListaDeCondiciones() {
		List<Condicion> listaDeCondiciones2 = new ArrayList<Condicion>();
		listaDeCondiciones2.addAll(listaDeCondiciones);
		return listaDeCondiciones2;
	}*/
}
