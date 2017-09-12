package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;


public class RepositorioDeIndicadores {
	//private static List<Indicador> listaDeIndicadores = new ArrayList<>();

	/*public static List<Indicador> getListaDeIndicadores() {
		return listaDeIndicadores;
	}*/
	
	public static List<Indicador> traerIndicadoresDeLaDB() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return em.createQuery("from Indicador", Indicador.class).getResultList();
	}

	
	public static void agregarIndicador(Indicador indicador) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();

		List<Indicador> listaDeIndicadores = em.createQuery("from Indicador e where e.nombre = :nombre", Indicador.class)
				.setParameter("nombre", "%" + indicador.getNombre() + "%").getResultList();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (listaDeIndicadores.isEmpty())

			em.persist(indicador);
		else {
			System.out.println("esta parte no esta hecha, habria que modificar la empresa, o dejarla como esta");
			System.out.println(listaDeIndicadores.size());
		}

		tx.commit();		
	}
	
	 
	/*public static void agregarIndicador(Indicador indicador) {
		listaDeIndicadores.removeIf(ind-> ind.getNombre().equals(indicador.getNombre()));
		listaDeIndicadores.add(indicador);
	}*/
	
	
}
