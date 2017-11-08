package model.condicionesYMetodologias;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import model.Empresa;
import model.Indicador;
import model.Calculos.criterioDeAceptacionDeCondicion;
import model.Excepciones.IdentificadorInexistente;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CondicionesFiltro extends Condicion {
	
	protected CondicionesFiltro() {
	}

	protected CondicionesFiltro(Indicador indicador2, criterioDeAceptacionDeCondicion criterio2, String nombre2){
		super(indicador2, criterio2, nombre2);
	}
}
