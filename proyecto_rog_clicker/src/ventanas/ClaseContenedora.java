package ventanas;

import java.util.ArrayList;

import clases.Usuario;

public class ClaseContenedora {

	
	
	
	public static void main(String[] args) {
		ClaseContenedora c1 = new ClaseContenedora();
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		Usuario u1 = new Usuario("Jorge","Alonso","JorgeAlonsoSuS","pepelotas1");
		Usuario u2 = new Usuario("David","Barrenechea","DavidBarrenechea","pepelotas2");
		Usuario u3 = new Usuario("Daniel","Galean","DjLamentablisimo","pepelotas3");
		Usuario u4 = new Usuario("Asier","Belloso","BellosoAsier","pepelotas4");
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		listaUsuarios.add(u3);
		listaUsuarios.add(u4);
		
	}
}
