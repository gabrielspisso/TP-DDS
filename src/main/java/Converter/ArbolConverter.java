package Converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import model.Indicador;
import model.Arbol.Operaciones.NODO;
import model.Builders.IndicadorBuilder;

@Converter
public class ArbolConverter implements AttributeConverter<NODO, String> {
	@Override
	public String convertToDatabaseColumn(NODO nodo) {
		if(nodo == null)
			return null;
		
		return nodo.mostrarFormula();
	}

	
		 @Override
		 public NODO convertToEntityAttribute(String Nodo) {
			 
			 if(Nodo == null)
				 return null;
			 
			 Indicador indicador = IndicadorBuilder.Build("nombre=" + Nodo + ";");
			 return indicador.getArbol();
		 
		 }
		 
}
