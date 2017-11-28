package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.condicionesYMetodologias.Condicion;

public class RepositorioDeEmpresas extends Repositorio implements RepositorioDeEmpresasInterfaz{
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
	public void agregarEmpresas(Empresa empresaAAgregar) {
		this.addInstanceToDB(Empresa.class, empresaAAgregar);
	}

	// entityManager().createQuery("from Consultora c where c.nombre like :nombre",
	// Consultora.class).setParameter("nombre", "%" + nombre + "%").getResultList();

	public List<Empresa> traerEmpresasDeLaDB() {
		return this.getFromDB(Empresa.class);
	}

	public boolean existe(String nombre) {
		return this.existe(nombre, Empresa.class);
	}
	public void borrar(String nombre) {
		this.borrar(nombre, Empresa.class);
	}

	public Empresa buscar(String nombre) {
		return this.buscar(nombre, Empresa.class);
	}

	@Override
	public Empresa buscarPorId(String params) {
		// TODO Auto-generated method stub
		return this.buscar(params, Empresa.class);
	}
	
}
