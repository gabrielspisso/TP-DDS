package repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.Metodologia;

public  class Repositorio {

	public void metodoGenerador(){
		this.getFromDB(Repositorio.class,"");
		this.getFromDB(Metodologia.class,"");
	}

	private <T> List<T>  getFromDB(Class<T> class1,String nombreDeDB) {
		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from "+nombreDeDB, class1).getResultList();		
	}
	private <T> void  addInstanceToDB(Class<T> class1,String nombre,T ObjetoAPersistir,String nombreDeBaseDeDatos) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();

		List<T> listaDeCondiciones = em
				.createQuery("from "+nombreDeBaseDeDatos+" e where e.nombre like :nombre", class1)
				.setParameter("nombre", nombre).getResultList();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		if (listaDeCondiciones.isEmpty())

			em.persist(ObjetoAPersistir);
		else {
			//System.out.println("esta parte no esta hecha, habria que modificar la empresa, o dejarla como esta");
			// Empresa empresa1 = listaDeEmpresas.get(0);
			// em.remove(empresa1);
			// empresa1.setBalances(empresaAAgregar.getBalances());
			// em.persist(empresaAAgregar);
		}

		tx.commit();
		
	}
}
