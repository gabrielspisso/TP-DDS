package viewModel;
import parser.test;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Balance;
import model.CargadorDeEmpresas;
import model.Cuenta;
import model.Empresa;
import repositoriosDeEmpresa.RepositorioDeEmpresa;

@Observable
public class crearIndicadoresViewModel {
	private Empresa empresaActual; //= new Empresa("",Arrays.asList());
	private Balance balanceActual ;//= new Balance("","",Arrays.asList());
	private List<Balance> balances;
	
	private String indicadorActual;

	public String getIndicadorActual() {
		return indicadorActual;
	}


	public void setIndicadorActual(String indicadorActual) {
		this.indicadorActual = indicadorActual;
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
		return RepositorioDeEmpresa.mostrarEmpresas();
	}
	
	public void crearIndicador(){
		//test.obtenerTokens(ruta)
	}

}
