package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

@Entity
@Observable
@Table(name = "Empresa",uniqueConstraints= @UniqueConstraint(columnNames={"nombre"}))
public class Empresa {
	
	@SuppressWarnings("unused")
	private Empresa() {}
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}


	private String nombre;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id")
	private List<Balance> balances;
	
	public List<Balance> getBalances() {
		return balances;
	}
	public Empresa(String nombre,List<Balance> balances) {
		this.nombre = nombre;
		this.balances = balances;
	}
	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	
	@Override
	public boolean equals(Object Objeto){
		try{
			Empresa empresa = (Empresa) Objeto;			
			return empresa.nombre.equals(this.toString());
		}
		catch(Exception ex){
			return false;
		}
		//return empresa.nombre == this.nombre;
		
	}
	public void agregarBalance(Balance balance) {
		
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			balances.add(balance);
			tx.commit();
		}
		catch(Exception ex) {
			tx.rollback();
		}
	}
}
