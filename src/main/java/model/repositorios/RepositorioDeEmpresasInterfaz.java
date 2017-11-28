package model.repositorios;

import java.util.List;

import model.Empresa;

public interface RepositorioDeEmpresasInterfaz {
	public void agregarEmpresas(Empresa empresaAAgregar); 
	public List<Empresa> traerEmpresasDeLaDB();
	public boolean existe(String nombre) ;
	public void borrar(String nombre);
	public Empresa buscarPorId(String params);
}
