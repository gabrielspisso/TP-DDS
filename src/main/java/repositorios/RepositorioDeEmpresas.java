package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

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
		EntityManager em = PerThreadEntityManagers.getEntityManager();

		List<Empresa> listaDeEmpresas = em.createQuery("from Empresa e where e.nombre like :nombre", Empresa.class)
				.setParameter("nombre", "%" + empresaAAgregar.toString() + "%").getResultList();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (listaDeEmpresas.isEmpty())

			em.persist(empresaAAgregar);
		else {
			System.out.println("esta parte no esta hecha, habria que modificar la empresa, o dejarla como esta");
			//Empresa empresa1 = listaDeEmpresas.get(0);
			//em.remove(empresa1);
			//empresa1.setBalances(empresaAAgregar.getBalances());
			//em.persist(empresaAAgregar);
		}

		tx.commit();
	}

	// entityManager().createQuery("from Consultora c where c.nombre like :nombre",
	// Consultora.class).setParameter("nombre", "%" + nombre + "%").getResultList();

	public static List<Empresa> traerEmpresasDeLaDB() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return em.createQuery("from Empresa", Empresa.class).getResultList();
	}

}
