package repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.Metodologia;

public  class Repositorio {


	public static <T> List<T>  getFromDB(Class<T> clase,String tabla) {		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from "+tabla, clase).getResultList();		
	}
	public static <T> void  addInstanceToDB(Class<T> class1,T ObjetoAPersistir,String tabla) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();

		List<T> listaDeCondiciones = em
				.createQuery("from "+tabla+" e where e.nombre = :nombre", class1)
				.setParameter("nombre", ObjetoAPersistir.toString()).getResultList();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (listaDeCondiciones.isEmpty())
			em.persist(ObjetoAPersistir);
		else {
			// Aca fijarse de que si ya esta en la BD la empresa o lo que sea que estes cargando, te tira un errorS
		}

		tx.commit();
		
	}
		
	public static <T> boolean existe(String nombre,Class<T> clase, String tabla) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return !em.createQuery("from Consultora c where c.nombre like :nombre", clase) //
		        .setParameter("nombre", "%" + nombre + "%") //
		        .getResultList().isEmpty();
	}
}
