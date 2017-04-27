package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.CargadorDeEmpresas;
import model.Balance;
import model.Cuenta;
import model.Empresa;

@Observable
public class mostrarCuentaViewModel {
	private Empresa empresaActual= new Empresa(Arrays.asList());
	private Balance balanceActual= new Balance("",Arrays.asList());
	private Cuenta cuentaActual = new Cuenta("",0);
	private List<Empresa> listaDeEmpresas;
	private List<Balance> balances;
	private List<Cuenta> cuentas;

	
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
		ObservableUtils.firePropertyChanged(this, "cuentaActual");
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
		ObservableUtils.firePropertyChanged(this, "balanceActual");
	}

	public Empresa getEmpresaActual() {
		return empresaActual;
	}
	
	public void setEmpresaActual(Empresa empresaActual) {
		this.empresaActual = empresaActual;
		ObservableUtils.firePropertyChanged(this, "balances");
	}
	
	public Balance getBalanceActual() {
		return balanceActual;
	}

	public void setBalanceActual(Balance balanceActual) {
		this.balanceActual = balanceActual;
		ObservableUtils.firePropertyChanged(this, "cuentas");
	}

	public Cuenta getCuentaActual() {
		return cuentaActual;
	}

	public void setCuentaActual(Cuenta cuentaActual) {
		this.cuentaActual = cuentaActual;
		ObservableUtils.firePropertyChanged(this, "valorActual");
	}
	
	public List<Balance> getBalances(){
		return (empresaActual == null) ? null : empresaActual.getBalances();
	}
	public List<Cuenta> getCuentas(){
		return (balanceActual == null) ? null : balanceActual.getCuentas();
	}
	public String getPeriodo(){		
		return (balanceActual == null) ? null : balanceActual.getPeriodo();
	}
	@Dependencies({"cuentaActual"})
	public int getValorActual(){
		return (cuentaActual == null) ? 0 : cuentaActual.getValor();
	}
	
	public List<Empresa> getListaDeEmpresas(){
		return CargadorDeEmpresas.obtenerCuentasEmpresas();
	}

}
