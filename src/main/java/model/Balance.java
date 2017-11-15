package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "indicadorPrecalculado_id")
	private List<Resultado> indicadoresPrecalculado = new ArrayList<Resultado>();
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
			return balance.getAnio() == this.getAnio() && this.getPeriodo().equals(balance.getPeriodo());
		}
		catch(Exception ex){
			return false;
		}
	}
	public void agregarIndicadorPrecalculado(Indicador indicador, Double valor) {
		Resultado resultado = new Resultado(indicador,valor);
		indicadoresPrecalculado.add(resultado);
	}
	public Double buscarEnListaDeResultados(Indicador indicador, Empresa empresa) {
		Resultado res = indicadoresPrecalculado.stream().filter(res1 -> res1.getIndicador().getNombre().equals(indicador.getNombre())).findFirst().get();
		return res.getValor();
	}
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);		
	}
	
}
