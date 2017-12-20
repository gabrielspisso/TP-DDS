package model.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.set.ListToSetAdapter;

import model.Cuenta;
import model.Empresa;

public class RepositorioDeEmpresasMock implements RepositorioDeEmpresasInterfaz{
	List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();
	public  void agregarEmpresas(Empresa empresaAAgregar) {
		listaDeEmpresas.add(empresaAAgregar);
	}

	// entityManager().createQuery("from Consultora c where c.nombre like :nombre",
	// Consultora.class).setParameter("nombre", "%" + nombre + "%").getResultList();

	public  List<Empresa> traerEmpresasDeLaDB() {
		return listaDeEmpresas;
	}

	public  boolean existe(String nombre) {
		return listaDeEmpresas.stream().anyMatch(x->x.getNombre().equals(nombre));
	}
	public  void borrar(String nombre) {
		listaDeEmpresas.removeIf(x->x.getNombre().equals(nombre));
	}

	@Override
	public Empresa buscarPorId(String params) {
		// TODO Auto-generated method stub
		if(listaDeEmpresas.stream().anyMatch(emp->emp.getId() == Long.parseLong(params))) {
			return listaDeEmpresas.stream().filter(emp->emp.getId() == Long.parseLong(params)).findFirst().get();
		}
		return  null;
	}

	public Empresa buscar(String string) {
		// TODO Auto-generated method stub
		if(listaDeEmpresas.stream().anyMatch(emp->emp.getNombre().equals(string))) {
			return listaDeEmpresas.stream().filter(emp->emp.getNombre().equals(string)).findFirst().get();
		}
		return  null;
	}

	@Override
	public void ActualizarCuenta(Cuenta cuenta, int valor) {
		
		cuenta.setValor(valor);
	}
	
}
