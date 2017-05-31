package viewModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Balance;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;

@Observable
public class mostrarIndicadorViewModel {
	private Empresa empresaActual; //= new Empresa("",Arrays.asList());
	private Balance balanceActual ;//= new Balance("","",Arrays.asList());
	private List<Balance> balances;
	private Indicador indicadorActual;
	private List<Indicador> indicadores;
	private List<Empresa> empresas = new ArrayList<>();
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}


	public void setBalances(List<Balance> balances) {
		this.balances = balances;
		//this.setBalanceActual(balances.get(0));
	}


	public Empresa getEmpresaActual() {
		return empresaActual;
	}
	
	public void setEmpresaActual(Empresa empresaActual) {
		try{
			this.empresaActual = empresaActual;
			this.setBalances(empresaActual.getBalances());				
		}
		catch(RuntimeException ex){
			this.empresaActual = empresaActual;
			this.balances = null;
			setBalanceActual(null);
		}		
	}
	
	public Balance getBalanceActual() {
		return balanceActual;
	}

	public void setBalanceActual(Balance balanceActual) {
		this.balanceActual = balanceActual;
	}

	
	public List<Balance> getBalances(){
		return balances;
	}

	public String getPeriodo(){		
		return balanceActual.getPeriodo();
	}
	
	
	public List<Empresa> getEmpresas(){
		return RepositorioDeEmpresas.mostrarEmpresas();
	}
	

	public Indicador getIndicadorActual() {
		return indicadorActual;
	}


	public void setIndicadorActual(Indicador indicadorActual) {
		this.indicadorActual = indicadorActual;
	}


	public List<Indicador> getIndicadores() {
		return RepositorioDeIndicadores.getListaDeIndicadores();
	}


	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	public String obtenerValorDeIndicador(){
			return 
					Double.toString(indicadorActual.calcularValor(balanceActual.getCuentas(), RepositorioDeIndicadores.getListaDeIndicadores()));			
	}
	
	public void cargarIndicadoresDefinidos(){
		RepositorioDeIndicadores.cargarIndicadoresDefinidos();
	}
}
