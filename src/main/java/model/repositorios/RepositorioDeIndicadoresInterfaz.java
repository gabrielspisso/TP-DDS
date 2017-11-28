package model.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Indicador;
import model.Resultado;

public interface RepositorioDeIndicadoresInterfaz {
	public  List<Indicador>  traerIndicadoresDeLaDB(Long id);

public  void agregarIndicador(Indicador indicador);
public  boolean existe(String nombre) ;
public  void borrar(String nombre);

public boolean lePertenece(String id_indicador, Long id_usuario);

public Indicador buscarPorId(String params);

public Indicador buscar(String nombre);

public void agregarResueltado(List<Resultado> indicadoresPrecalculado, Resultado resultado);
}
