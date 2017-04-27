package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.Window;
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
	private List<Balance> balances;
	private List<Cuenta> cuentas;

	
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}


	public void setBalances(List<Balance> balances) {
		this.balances = balances;
		//this.setBalanceActual(balances.get(0));
	}


	public Empresa getEmpresaActual() {
		return empresaActual;
	}
	
	public void setEmpresaActual(Empresa empresaActual) {
				this.empresaActual = empresaActual;
//ObservableUtils.firePropertyChanged(this, "cuentas");
			this.setBalances(empresaActual.getBalances());	
			//this.setCuentas(balances.get(0).getCuentas());

	}
	
	public Balance getBalanceActual() {
		return balanceActual;
	}

	public void setBalanceActual(Balance balanceActual) {
		this.balanceActual = balanceActual;
		if(balanceActual != null){
			this.setCuentas(balanceActual.getCuentas());
			//this.setCuentaActual(balanceActual.getCuentas().get(0));
			
		}
		else{
			this.setCuentaActual(null);
		}
	}

	public Cuenta getCuentaActual() {
		return cuentaActual;
	}

	public void setCuentaActual(Cuenta cuentaActual) {
		this.cuentaActual = cuentaActual;
	}
	
	
	public List<Balance> getBalances(){
		return balances;
	}
	public List<Cuenta> getCuentas(){
		return cuentas;
	}
	public String getPeriodo(){		
		return balanceActual.getPeriodo();
	}
	public void getValorActual(Window<?> owner){

		MessageBox messageBox = new MessageBox( owner, Type.Information);
		messageBox.setMessage("El valor de "+this.getCuentaActual().getNombre()+" es "+Integer.toString(cuentaActual.getValor()));
		messageBox.open();
	}
	
	public List<Empresa> getListaDeEmpresas(){
		return CargadorDeEmpresas.obtenerCuentasEmpresas();
	}

}
