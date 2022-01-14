package proyecto_rog_clickers;

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
		assertEquals(5, lista.size());
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
		cc.guardarDBUsuario("UsuarioTest.db",100, 100, 100, "pepito", "grillo");
		ArrayList<Usuario> lista = cc.sacarUsuarios("UsuarioTest.db");
		
		assertTrue((lista.get(lista.size()-1).getnUsuario().equals("pepito")));	
	}
	
	//Comprueba que se borra correctamente un usuario de la base de datos
	@Test
	public void testBorrarDBUsuario() {
		cc.borrarDBUsuario("UsuarioTest.db","pepito");
		ArrayList<Usuario>lista = cc.sacarUsuarios("UsuarioTest.db");
		assertEquals(5,lista.size());
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
	

	//Comprueba que se borra correctamente un edificio de la base de datos
	@Test
	public void testBorrrarDBEdificio() {
		ArrayList<Edificios> lista = cc.sacarEdificios("UsuarioTest.db");
		cc.borrarDBEdificio("UsuarioTest.db","prueba");
		assertEquals(17,lista.size());
	
	}

	
	@Test
	public void testGuardarEdificiosPersonales() {
		cc.guardaredificiosPersonales("UsuarioTest.bd", "paco", "prueba");
		ArrayList<ArrayList<String>> lista = cc.sacarEdificiosPersonales("UsuarioTest.bd", "paco");
		assertEquals(18,lista.size());
	}
	@Test
	public void testSacarEdificiosPersonales() {
		ArrayList<ArrayList<String>> lista = cc.sacarEdificiosPersonales("UsuarioTest.bd", "paco");
		assertEquals(17,lista.size());
		
	}
	@Test
	public void testActualizarPersonalEdifi() {
		cc.actualizarPersonalEdifi("UsuarioTest.bd", "paco", 7);
		ArrayList<ArrayList<String>> lista = cc.sacarEdificiosPersonales("UsuarioTest.bd", "paco");
		for (ArrayList<String> i: lista) {
				assertEquals(7,i.get(1));
			
		}
	}
	@Test
	public void testActualizarPersonalEdifi2() {
		cc.actualizarPersonalEdifi2("UsuarioTest.bd", "paco", "Paga de la Yaya", 1);
		ArrayList<ArrayList<String>> lista = cc.sacarEdificiosPersonales("UsuarioTest.bd", "paco");
		assertEquals(1,lista.get(1).get(1));
	}

		

}
