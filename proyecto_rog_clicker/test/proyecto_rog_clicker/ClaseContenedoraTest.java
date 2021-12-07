package proyecto_rog_clicker;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import org.junit.Test;

import clases.Edificios;
import clases.Usuario;
import ventanas.ClaseContenedora;

public class ClaseContenedoraTest {

	private ClaseContenedora cc = new ClaseContenedora();
	

	@Test
	public void testSacarUsuarios() {
		ArrayList<Usuario> lista = cc.sacarUsuarios();
		assertEquals(2, lista.size());
	}

	@Test
	public void testActualizarPartida() {
		
	}
	
	@Test
	public void testSacarEdificios() {
		ArrayList<Edificios> lista = cc.sacarEdificios();
		lista.add(new Edificios("Barco",100,100,100,""));
		assertEquals(18,lista.size());
	}
	

	@Test
	public void testGuardarDBUsuario() {
		cc.guardarDBUsuario(100, 100, 100, "Pepito", "grillo", 100);	
		ArrayList<Usuario> lista = cc.sacarUsuarios();
		assertTrue((lista.get(lista.size()-1).getnUsuario().equals("pepito")));	
	}

	@Test
	public void testBorrarDBUsuario() {
		cc.borrarDBUsuario("pepito");
		ArrayList<Usuario> lista = cc.sacarUsuarios();
		assertEquals(2,lista.size());
	}
	
	@Test
	public void testCambiarContraseñaBD() {
		
	}
	
	@Test
	public void testGuardarDBEdificio() {
		
	}
	
	@Test
	public void testBorrrarDBEdificio() {
		
	}
	
	@Test
	public void testBorrarMejoras() {
		
	}
	
	

}
