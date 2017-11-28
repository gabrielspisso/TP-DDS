package model.repositorios;

import java.util.List;

import model.condicionesYMetodologias.Metodologia;

public interface RepositorioDeMetodologiasInterfaz {
	public List<Metodologia> traerMetodologiasDeLaDB(Long id);

	public void agregarMetodologia(Metodologia metodologiaAAgregar);

	public List<Metodologia> traerMetodologiasDeLaDB();

	public boolean existe(String nombre);

	public void borrar(String nombre);

	public boolean lePertenece(String id_metodologia, Long id_usuario);

	public Metodologia buscarPorId(Long idMetodologia);
}
