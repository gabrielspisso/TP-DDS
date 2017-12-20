package model.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.Indicador;
import model.Resultado;

public class RepositorioDeIndicadoresMockeado implements RepositorioDeIndicadoresInterfaz {
	List<Indicador> listaDeIndicadores = new ArrayList<Indicador>();
	public  List<Indicador>  traerIndicadoresDeLaDB(Long id) {		
		return listaDeIndicadores.stream().filter(x->x.getUsuario().getId() == id) .collect(Collectors.toList());
}

public void agregarIndicador(Indicador indicador) {
	listaDeIndicadores.add(indicador);
}


public  boolean existe(String nombre) {
	return listaDeIndicadores.stream().allMatch(indicador-> indicador.getNombre().equals(nombre));
}
public  void borrar(String nombre) {
	listaDeIndicadores.removeIf(indicador-> indicador.getNombre().equals(nombre));
}

public  boolean lePertenece(String id_indicador, Long id_usuario) {
	return listaDeIndicadores.stream().anyMatch(ind-> ind.getUsuario().getId() == id_usuario && ind.getId() == Long.parseLong(id_indicador));
}

@Override
public Indicador buscarPorId(String params) {
	// TODO Auto-generated method stub
	if(listaDeIndicadores.stream().anyMatch(ind->ind.getId() == Long.parseLong(params))) {
		return listaDeIndicadores.stream().filter(ind->ind.getId() == Long.parseLong(params)).findFirst().get();
	}
	return  null;
}

@Override
public Indicador buscar(String nombre) {
	// TODO Auto-generated method stub
	if(listaDeIndicadores.stream().anyMatch(ind->ind.getNombre().equals(nombre))) {
		return listaDeIndicadores.stream().filter(ind->ind.getNombre().equals(nombre)).findFirst().get();
	}
	return  null;
}

@Override
public void agregarResueltado(List<Resultado> indicadoresPrecalculado, Resultado resultado) {
	// TODO Auto-generated method stub
	indicadoresPrecalculado.add(resultado);

}

@Override
public void limpiarIndicadores(List<Resultado> indicadoresPrecalculado, Cuenta cuenta) {
	indicadoresPrecalculado.removeIf(res-> res.fueAfectadoPor(cuenta));
	
}
}
