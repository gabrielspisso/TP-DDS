package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Resultado;
import model.Excepciones.DuplicateExecption;


public class RepositorioDeIndicadores extends Repositorio implements RepositorioDeIndicadoresInterfaz{


	public List<Indicador>  traerIndicadoresDeLaDB(Long id) {		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from Indicador i where i.usuario.id = :id", Indicador.class)
					.setParameter("id", id)
					.getResultList();		
	}
	
	public void agregarIndicador(Indicador indicador) {
		
		Indicador indi = buscar(indicador.getNombre());
		if(indi!=null){
			if(indi.getUsuario() == indicador.getUsuario()){
				throw new DuplicateExecption("YA EXISTIA ESTE INDICADOR CON ESE USUARIO NAAB");
			}
		}
		
		this.addInstanceToDB(Indicador.class,  indicador);
	}
	

	public boolean existe(String nombre) {
		return this.existe(nombre, Indicador.class);
	}
	public void borrar(String nombre) {
		this.borrar(nombre, Indicador.class);
	}
	
	public boolean lePertenece(String id_indicador, Long id_usuario) {
		Indicador ind = this.buscarPorId(Long.valueOf(id_indicador).longValue(), Indicador.class);
		if(ind == null) return false;
		return ind.getUsuario().getId() == id_usuario;
	}

	public Indicador buscar(String nombre) {
		return this.buscar(nombre,Indicador.class);
	}

	
	public Indicador buscarPorId(String params) {
		return buscarPorId(params,Indicador.class);
	}
	
	@Override
	public void agregarResueltado(List<Resultado> indicadoresPrecalculado, Resultado resultado) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			indicadoresPrecalculado.add(resultado);
			em.persist(resultado);
			tx.commit();
		}
		catch(Exception ex) {
			tx.rollback();
		}
		
	}
	public void limpiarIndicadores(List<Resultado> indicadoresPrecalculado,Cuenta cuenta) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			indicadoresPrecalculado.removeIf(res-> res.fueAfectadoPor(cuenta));
			tx.commit();
		}
		catch(Exception ex) {
			tx.rollback();
		}
	}
	
}
