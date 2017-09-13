package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesYMetodologias.Metodologia;
import model.Indicador;

public class RepositorioDeMetodologias {
	//private static List<Metodologia> listaDeMetodologias = new ArrayList<>();

	
	/*public static List<Metodologia> getListaDeMetodologias() {
	*	return listaDeMetodologias;
	*}

	/*public static void agregarMetodologia(Metodologia metodologia) {
	*	listaDeMetodologias.removeIf(metodologia2-> metodologia2.getNombre().equals(metodologia.getNombre()));
	*	listaDeMetodologias.add(metodologia);
	}*/
	
	public static void agregarMetodologia(Metodologia metodologiaAAgregar) {
		  EntityManager em = PerThreadEntityManagers.getEntityManager();

		  List<Metodologia> listaDeMetodologias = em.createQuery("from Metodologia m where m.nombre = :nombre", Metodologia.class)
		    .setParameter("nombre", metodologiaAAgregar.toString()).getResultList();

		  EntityTransaction tx = em.getTransaction();

		  tx.begin();

		  if (listaDeMetodologias.isEmpty())

		   em.persist(metodologiaAAgregar);
		  
		  else {
		   System.out.println("esta parte no esta hecha, habria que modificar la empresa, o dejarla como esta");
		   //Empresa empresa1 = listaDeEmpresas.get(0);
		   //em.remove(empresa1);
		   //empresa1.setBalances(empresaAAgregar.getBalances());
		   //em.persist(empresaAAgregar);
		  }

		  tx.commit();
		 }
	
		 public static List<Metodologia> traerMetodologiasDeLaDB() {
			 
			 EntityManager em = PerThreadEntityManagers.getEntityManager();
			 return em.createQuery("from Metodologia", Metodologia.class).getResultList();
		 }
	
}
