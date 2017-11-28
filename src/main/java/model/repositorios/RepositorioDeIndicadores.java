package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;


public class RepositorioDeIndicadores extends Repositorio implements RepositorioDeIndicadoresInterfaz{
	//private static List<Indicador> listaDeIndicadores = new ArrayList<>();

	/*public static List<Indicador> getListaDeIndicadores() {
		return listaDeIndicadores;
	}*/
	

	public List<Indicador>  traerIndicadoresDeLaDB(Long id) {		
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from Indicador i where i.usuario.id = :id", Indicador.class)
					.setParameter("id", id)
					.getResultList();		
	}
	
	public void agregarIndicador(Indicador indicador) {
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
	
}
