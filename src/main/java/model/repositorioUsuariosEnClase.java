package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class repositorioUsuariosEnClase {

	List<Usuario> repo = new ArrayList<>();
	
	public List<Usuario> lista(){
		return Arrays.asList(new Usuario("roli","roli@serverderoli.com","1234","Soy un ayudante cruel "),
				new Usuario("Facu", "facugraso@serverdefranco.com", "4666", "A DO MANOO"),
				new Usuario("favio", "harryelpollo@serverdefranco.com", "3333", "Como puta en cabaret"),
				new Usuario("dante", "dantemaiori@serverdefranco.com", "5666", "Me Gusta el Terraria"),
				new Usuario("jessica", "jessica@serverdefranco.com", "4444", "baia baia"));
	}
	
}
