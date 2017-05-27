package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import model.CargadorDeEmpresas;
import model.Balance;
import model.Cuenta;
import model.Empresa;

@Observable
public class mostrarCuentaViewModel {
	private Empresa empresaActual; //= new Empresa("",Arrays.asList());
	private Balance balanceActual ;//= new Balance("","",Arrays.asList());
	private Cuenta cuentaActual; //= new Cuenta("",0);
	private List<Balance> balances;
	private List<Cuenta> cuentas;
	private List<Empresa> empresas;
	private String rutaArchivo;
	
	public void setRutaArchivo(String rutaArchivo){
		this.rutaArchivo = rutaArchivo;
	}
	
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	
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
		try{
			this.setCuentas(balanceActual.getCuentas());	
		}
		catch(RuntimeException ex){
			this.setCuentas(null);			
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
	
	/*public void mostrarValorCuentaSeleccionada(Window<?> owner){
		MessageBox messageBox;
		try{
			messageBox = new MessageBox( owner, Type.Information);
			//messageBox.setMessage("El valor de "+this.getCuentaActual().getNombre()+" es "+Integer.toString(cuentaActual.getValor()));
			messageBox.setMessage("El valor de " + this.getCuentaActual().getNombre() + " es " + cuentaActual.getValor());
		}
		catch (RuntimeException ex){
			messageBox = new MessageBox( owner, Type.Error);
			messageBox.setMessage("No has seleccionado algun campo");
		}
		messageBox.open();
	}*/
	
	public List<Empresa> getEmpresas(){
		return empresas;
	}
	/*public void cargarEmpresas(Window<?> owner){
		MessageBox messageBox;
		try
		{
			this.empresas = CargadorDeEmpresas.obtenerCuentasEmpresas(rutaArchivo);
			messageBox = new MessageBox( owner, Type.Information);
			messageBox.setMessage("Se han cargado exitosamente los datos!");
		}
		catch(RuntimeException ex)
		{
			messageBox = new MessageBox( owner, Type.Error);
			messageBox.setMessage(ex.getMessage());	
		}
		messageBox.open();
	}*/
	
	public void cargarEmpresas(){
		this.empresas = CargadorDeEmpresas.obtenerCuentasEmpresas(rutaArchivo);
	}

}
