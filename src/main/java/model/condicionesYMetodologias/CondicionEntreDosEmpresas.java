package model.condicionesYMetodologias;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.antlr.v4.runtime.atn.EmptyPredictionContext;

import model.Balance;
import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;

@Entity
public class CondicionEntreDosEmpresas extends Condicion {
	private CondicionEntreDosEmpresas() {
	}

	public CondicionEntreDosEmpresas(Indicador indicador, criterioDeAceptacionDeCondicion criterio,String nombre) {
		super(indicador, criterio, nombre);
	}

	public boolean cumpleLaCondicion(Empresa empresa) {
		return false;
	}
	
	@Transient
	public Empresa emppresaAux;
	
	public Empresa comparador(Empresa em1, Empresa em2) {
		return (criterio.cumpleCriterioDeAceptacionDeCondicion(indicador.calcularValor(em1.getBalances().get(0).getCuentas()),indicador.calcularValor(em2.getBalances().get(0).getCuentas())))?em1:em2;
	}
	
	@Override
	public boolean cumpleLaCondicionEmpresarial(Empresa empresa, List<Empresa> listaDeEmpresas) {
		if(!existeEseIndicadorParaEstaEmpresa(empresa,indicador) || listaDeEmpresas.size() == 0) {
			return false;  
		}
		emppresaAux = listaDeEmpresas.get(0);
			
		listaDeEmpresas.forEach(em->emppresaAux=comparador(emppresaAux, em));	
		
		return criterio.cumpleCriterioDeAceptacionDeCondicion(indicador.calcularValor(emppresaAux.getBalances().get(0).getCuentas()),indicador.calcularValor(empresa.getBalances().get(0).getCuentas()))? true: false;	
	}
}
