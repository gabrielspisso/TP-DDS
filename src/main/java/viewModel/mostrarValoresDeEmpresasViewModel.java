package viewModel;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.NullArgumentException;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;
import model.Balance;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioDeEmpresas;
import repositorios.RepositorioDeIndicadores;

@Observable
public class mostrarValoresDeEmpresasViewModel {
	private Empresa empresaActual; //= new Empresa("",Arrays.asList());
	private Balance balanceActual ;//= new Balance("","",Arrays.asList());
	private Cuenta cuentaActual; //= new Cuenta("",0);
	private Indicador indicadorActual;
	
	

	public Empresa getEmpresaActual() {
		return empresaActual;
	}
	
	public void setEmpresaActual(Empresa empresaActual) {
		try{
			this.empresaActual = empresaActual;
			ObservableUtils.firePropertyChanged(this, "balances");	
			ObservableUtils.firePropertyChanged(this, "seleccionoEmpresaYPeriodo");
		}
		catch(RuntimeException ex){
			this.empresaActual = empresaActual;
			setBalanceActual(null);
		}		
	}
	
	public Balance getBalanceActual() {
		return balanceActual;
	}

	public void setBalanceActual(Balance balanceActual) {
		this.balanceActual = balanceActual;
		ObservableUtils.firePropertyChanged(this, "cuentas");
		ObservableUtils.firePropertyChanged(this, "seleccionoEmpresaYPeriodo");
	}

	public Cuenta getCuentaActual() {
		return cuentaActual;
	}

	public void setCuentaActual(Cuenta cuentaActual) {
		this.cuentaActual = cuentaActual;
	}
	
	
	public List<Balance> getBalances(){
		try{
			return empresaActual.getBalances();
		}
		catch(NullPointerException ex){
			return null;
		}
	}
	public List<Cuenta> getCuentas(){
		try{
			return balanceActual.getCuentas();
		}
		catch(NullPointerException ex){
			return null;
		}
		
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


	
	public String obtenerValorDeIndicador(){
					return 	Double.toString(indicadorActual
							.calcularValor(balanceActual.getCuentas()));			
	}
	
	public boolean getSeleccionoEmpresaYPeriodo(){
		return balanceActual != null;
	}

}
