package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class repositorioUsuariosEnClase {

	List<Usuario> repo = new ArrayList<>();
	
	public static List<Usuario> lista(){
		return Arrays.asList(new Usuario("roli","roli@serverderoli.com","1234","Soy un ayudante cruel "),
				new Usuario("jessica", "jessica@serverdefranco.com", "1234", "baia baia"));
	}
	
}
