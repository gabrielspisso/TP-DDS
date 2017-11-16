package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Resultado {
		
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	Indicador indicador;
	Double valor;

	public Indicador getIndicador() {
		return indicador;
	}

	public Double getValor() {
		return valor;
	}
	protected Resultado() {
		
	}
	public Resultado(Indicador indicador, Double valor) {
		super();
		this.indicador = indicador;
		this.valor = valor;
	}

	public boolean fueAfectadoPor(Cuenta cuenta) {
		
		return indicador.contieneEsteToken(cuenta.getNombre());
	}

	
}
