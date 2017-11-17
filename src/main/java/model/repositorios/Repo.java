package model.repositorios;

public interface Repo {
	public <T> T buscarPorId(Long id, Class <T> clase);
}
