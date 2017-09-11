package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;

public class RepositorioDeEmpresas {
	private static List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();

	/*
	 * public static void agregarEmpresas(List<Empresa> listaDeEmpresas2){
	 * 
	 * listaDeEmpresas.removeIf(x-> listaDeEmpresas2.contains(x));
	 * listaDeEmpresas.addAll(listaDeEmpresas2); }
	 */

	public static List<Empresa> mostrarEmpresas() {
		List<Empresa> listaDeEmpresas2 = new ArrayList<Empresa>();
		listaDeEmpresas2.addAll(listaDeEmpresas);
		return listaDeEmpresas2;
	}

	public static void agregarEmpresas(Empresa empresaAAgregar) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();

		Empresa empresa = em.createQuery("from Empresa e where e.nombre = " + empresaAAgregar.toString(), Empresa.class)
				.getResultList().get(0);

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (empresa == null)

			em.persist(empresaAAgregar);
		else {
			System.out.println("esta parte no esta hecha, habria que modificar la empresa, o dejarla como esta");
		}

		tx.commit();
	}

	// entityManager().createQuery("from Consultora c where c.nombre like :nombre",
	// Consultora.class).setParameter("nombre", "%" + nombre + "%").getResultList();

	/*
	 * public static List<Empresa> mostrarEmpresas(){ return
	 * entityManager.createQuery("from Empresa", Empresa.class).getResultList();; }
	 */

}
