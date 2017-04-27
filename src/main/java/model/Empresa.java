package model;

import java.util.List;


public class Empresa {
	private List<Balance> balances;
	public List<Balance> getBalances() {
		return balances;
	}
	public Empresa(List<Balance> balances) {

		this.balances = balances;
	}
	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}
}
