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
import clases.Mejoras;
import clases.Usuario;
import ventanas.ClaseContenedora;

public class ClaseContenedoraTest {

	private ClaseContenedora cc = new ClaseContenedora();
	
	
	//Comprueba que la cantidad de usuarios guardados en la base de datos es correcta

	@Test
	public void testSacarUsuarios() {
		ArrayList<Usuario> lista = cc.sacarUsuarios("UsuarioTest.db");
		assertEquals(6, lista.size());
	}

	//Comprueba si los datos de juego de un jugador se han actualizado correctamente en la base de datos.
	
	@Test
	public void testActualizarPartida() {
		ArrayList<Usuario>lista = cc.sacarUsuarios("UsuarioTest.db");
		cc.actualizarPartida("UsuarioTest.db","admin", 0, 0, 0);
		for (Usuario u: lista) {
			if(u.getnUsuario().equals("admin")) {
				assertEquals(0,u.getDinero_click_personal());
				assertEquals(0,u.getDinero_por_segundo_personal());
				assertEquals(0,u.getDinero_total_personal());
			}
	}
	}
	
	//Comprueba que la cantidad de edificios guardados en la base de datos es correcta
	@Test
	public void testSacarEdificios() {
		ArrayList<Edificios> lista = cc.sacarEdificios("UsuarioTest.db");
		assertEquals(17,lista.size());
	}
	
	//Comprueba que se guarda de manera correcta una nuevo usuario en la base de datos
	@Test
	public void testGuardarDBUsuario() {
		ArrayList<Usuario> lista = cc.sacarUsuarios("UsuarioTest.db");
		cc.guardarDBUsuario("UsuarioTest.db",100, 100, 100, "pepito", "grillo");
		assertTrue((lista.get(lista.size()-1).getnUsuario().equals("pepito")));	
	}
	
	//Comprueba que se borra correctamente un usuario de la base de datos
	@Test
	public void testBorrarDBUsuario() {
		ArrayList<Usuario>lista = cc.sacarUsuarios("UsuarioTest.db");
		cc.borrarDBUsuario("UsuarioTest.db","pepito");
		assertEquals(6,lista.size());
	}
	
	//Comprueba que la contraseña se ha cambiado correctamente en la base de datos
	@Test
	public void testCambiarContraseñaBD() {
		ArrayList<Usuario>lista = cc.sacarUsuarios("UsuarioTest.db");
		cc.cambiarContraseñaBD("UsuarioTest.db","admin", "123", "012");
	for (Usuario u: lista) {
		if(u.getnUsuario().equals("admin")) {
			assertEquals("012",u.getContraseña());		
		}
	}	
	}
	
	//Comprueba que se guarda de manera correcta un nuevo edificio en la base de datos
	@Test
	public void testGuardarDBEdificio() {
	ArrayList<Edificios> lista = cc.sacarEdificios("UsuarioTest.db");
	cc.guardarDBEdificio("UsuarioTest.db","prueba", 100, 100, 100, "");
	assertTrue((lista.get(lista.size()-1).getNombre().equals("prueba")));	
	}
	
	//Comprueba que se borra correctamente un edificio de la base de datos
	@Test
	public void testBorrrarDBEdificio() {
		ArrayList<Edificios> lista = cc.sacarEdificios("UsuarioTest.db");
		cc.borrarDBEdificio("UsuarioTest.db","prueba");
		assertEquals(17,lista.size());
	
	}
	/**	
	@Test
	public void testGuardarMejoras() {
		
		
	}
	@Test
	public void testBorrarMejoras() {
		
	}
	@Test
	public void testGuardarEdificiosPersonales() {
		
	}
	@Test
	public void testSacarEdificiosPersonales() {
		
	}
	@Test
	public void testAñadirCantidades() {
		
	}
	@Test
	public void testActualizarPersonalEdifi() {
		
	}
	@Test
	public void testActualizarPersonalEdifi2() {
		
	}
	**/
		

}
