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
		assertEquals(17,lista.size());
	}
	

	@Test
	public void testGuardarDBUsuario() {
		ArrayList<Usuario> lista = cc.sacarUsuarios();
		cc.guardarDBUsuario(100, 100, 100, "pepito", "grillo", 100);
		assertTrue((lista.get(lista.size()-1).getnUsuario().equals("pepito")));	
	}

	@Test
	public void testBorrarDBUsuario() {
		ArrayList<Usuario>lista = cc.sacarUsuarios();
		cc.borrarDBUsuario("pepito");
		assertEquals(2,lista.size());
	}
	
	@Test
	public void testCambiarContraseñaBD() {
		
	}
	
	@Test
	public void testGuardarDBEdificio() {
	ArrayList<Edificios> lista = cc.sacarEdificios();
	cc.guardarDBEdificio("prueba", 100, 100, 100, "");
	assertTrue((lista.get(lista.size()-1).getNombre().equals("pepito")));	
	}
	
	@Test
	public void testBorrrarDBEdificio() {
		ArrayList<Edificios> lista = cc.sacarEdificios();
		cc.borrarDBEdificio("prueba");
		assertEquals(17,lista.size());
		
	}

	@Test
	public void testBorrarMejoras() {
		
	}
	
	

}
