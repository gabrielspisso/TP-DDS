package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Excepciones.NoItemSelectedException;
import model.repositorios.Repositorio;
import model.repositorios.RepositorioDeIndicadores;
import model.repositorios.RepositorioDeIndicadoresInterfaz;


@Entity
@Table(name = "Balance")
public class Balance {
	
	@SuppressWarnings("unused")
	private Balance() {}
	
	@Id
	@GeneratedValue
	private Long id;

	private String periodo;
	private int anio;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "balance_id")
	private List<Cuenta> cuentas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name = "indicadorPrecalculado_id")
	private List<Resultado> indicadoresPrecalculado;
	public List<Resultado> getIndicadoresPrecalculado() {
		return indicadoresPrecalculado;
	}
	public String getPeriodo() {
		return periodo;
	}
	public Balance(int anio,String periodo, List<Cuenta> cuentas) {
		this.anio = anio;
		this.periodo = periodo;
		this.cuentas = cuentas;
		this.indicadoresPrecalculado= new ArrayList<Resultado>();
	}
	public  List<Cuenta>getCuentas() {
		return cuentas;
	}

	public int getAnio(){
		return anio;
	}
	@Override
	public String toString() {
		return this.getPeriodo() +" "+ this.getAnio();
	}

	@Override
	public boolean equals(Object Objeto){
		try{
			Balance balance = (Balance) Objeto;
			System.out.println( balance.getAnio() == this.getAnio() && this.getPeriodo().equals(balance.getPeriodo()) );
			return balance.getAnio() == this.getAnio() && this.getPeriodo().equals(balance.getPeriodo());
		}
		catch(Exception ex){
			return false;
		}
	}
	public void agregarIndicadorPrecalculado(Indicador indicador, Double valor,RepositorioDeIndicadoresInterfaz repo) {
		Indicador indicadorDeDB = repo.buscar(indicador.getNombre());
		Resultado resultado;
		if(indicadorDeDB != null) {
			 resultado = new Resultado(indicadorDeDB,valor);
		}
		else {
			 resultado = new Resultado(indicador,valor);	
		}
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
	public void setIndicadoresPrecalculado(List<Resultado> indicadoresPrecalculado) {
		this.indicadoresPrecalculado = indicadoresPrecalculado;
	}
	public Double buscarEnListaDeResultados(Indicador indicador) {
			Resultado res = indicadoresPrecalculado.stream().filter(res1 -> res1.getIndicador().getNombre().equals(indicador.getNombre())).findFirst().get();
			return res.getValor();			
			
	}
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);		
	}
	public boolean estaPrecalculadoElIndicador(Indicador indicador) {
		return indicadoresPrecalculado.stream().anyMatch(res1 -> res1.getIndicador().getNombre().equals(indicador.getNombre()));
	}
	public void limpiarIndicadoresPrecalculados(Cuenta cuenta) {
		if(indicadoresPrecalculado != null) {
			
			if(!indicadoresPrecalculado.isEmpty()) {
				
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
		
	}
	
}
