package model;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import Calculos.Mayor;
import condicionesYMetodologias.Condicion;
import condicionesYMetodologias.CondicionConAño;
import condicionesYMetodologias.Metodologia;
import model.Builders.IndicadorBuilder;

public class pruebaLeerMetodologias {
	public static void main(String[] args) {
		
		Indicador indicador = IndicadorBuilder.Build("indicador1=FREE CASH FLOW+4;");
		System.out.println("hola");
		CondicionConAño test = new CondicionConAño(indicador,new Mayor(),7,1);
		Metodologia metodologia = new Metodologia("Esto es una prueba",null,Arrays.asList(test));
		
		//escribirMetodologia(metodologia, "C:\\Users\\Santiago\\Documents\\GitHub\\2017-vn-group-18\\archivoMetodologias.txt");
		List<Metodologia> lista = IOs.leerArchivoDeMetodologias("C:\\Users\\Santiago\\Documents\\GitHub\\2017-vn-group-18\\archivoMetodologias.txt");
		System.out.println(lista.size());
		lista.get(0).getCondiciones().forEach(x->x.cumpleCondicion(null, null));
		
		System.out.println("llegue");
	}
}
