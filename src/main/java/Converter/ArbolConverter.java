package Converter;

import javax.persistence.AttributeConverter;

import model.Indicador;
import model.Arbol.Operaciones.NODO;
import model.Builders.IndicadorBuilder;

public class ArbolConverter implements AttributeConverter<NODO, String> {
	@Override
	public String convertToDatabaseColumn(NODO nodo) {
		return nodo.mostrarFormula();
	}

	
		 @Override
		 public NODO convertToEntityAttribute(String Nodo) {
			 Indicador indicador = IndicadorBuilder.Build("nombre=");
			 
			 return indicador.getArbol();
		 
		 }
		 
}
