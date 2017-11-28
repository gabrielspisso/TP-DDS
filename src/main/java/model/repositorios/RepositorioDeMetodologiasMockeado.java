package model.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.condicionesYMetodologias.Metodologia;

public class RepositorioDeMetodologiasMockeado implements RepositorioDeMetodologiasInterfaz {
	List<Metodologia> metodologias = new ArrayList<Metodologia>();
	public List<Metodologia>  traerMetodologiasDeLaDB(Long id) {		
		return metodologias.stream().filter(met-> met.getUsuario().getId() == id).collect(Collectors.toList());		
	}
	public void agregarMetodologia(Metodologia metodologiaAAgregar) {
		metodologias.add(metodologiaAAgregar);
	
	 }

	 public List<Metodologia> traerMetodologiasDeLaDB() {
		 return metodologias;
	 }
	 
	 public boolean existe(String nombre) {
			return metodologias.stream().anyMatch(met->met.getNombre().equals(nombre));
	}
	 public void borrar(String nombre) {
			metodologias.removeIf(met->met.getNombre().equals(nombre));
	}

	public boolean lePertenece(String id_metodologia, Long id_usuario) {
		return metodologias.stream().anyMatch(met-> met.getId() == Long.parseLong(id_metodologia) && met.getUsuario().getId() == id_usuario); 
	}
	@Override
	public Metodologia buscarPorId(Long idMetodologia) {
		// TODO Auto-generated method stub
		if(metodologias.stream().anyMatch(met->met.getId() == idMetodologia)) {
			return metodologias.stream().filter(met->met.getId() ==idMetodologia).findFirst().get();
		}
		return  null;
	}
	
}
