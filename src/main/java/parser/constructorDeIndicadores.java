package parser;

import java.util.List;

public class constructorDeIndicadores {

	List<TipoDeToken> listaDeTokens;
	
	public void leerIndicadores(){
		String ruta = "C:\u005c\u005cUsers\u005c\u005cGabriel\u005c\u005cDesktop\u005c\u005cprueba.txt";
		listaDeTokens = test.obtenerTokens(ruta);
		
		System.out.println("hola");
	}
}
