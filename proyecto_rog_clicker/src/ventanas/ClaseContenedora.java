package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Usuario;

public class ClaseContenedora {

	static int cooldown;
	static int dinero_click=VentanaUsuario.usuarioActual.getDinero_click_personal();
	static int dinero_por_segundo=VentanaUsuario.usuarioActual.getDinero_por_segundo_personal();
	static long dinero_total = VentanaUsuario.usuarioActual.getDinero_total_personal();

	
	
	public static void main(String[] args) {
		ClaseContenedora c1 = new ClaseContenedora();
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		/*Usuario u1 = new Usuario("Jorge","Alonso","JorgeAlonsoSuS","pepelotas1");
		Usuario u2 = new Usuario("David","Barrenechea","DavidBarrenechea","pepelotas2");
		Usuario u3 = new Usuario("Daniel","Galean","DjLamentablisimo","pepelotas3");
		Usuario u4 = new Usuario("Asier","Belloso","BellosoAsier","pepelotas4");
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		listaUsuarios.add(u3);
		listaUsuarios.add(u4);*/
		
		guardarDB(1, 10, 10000, "Justy", "root", 3600);
		
	}
	
	private static void guardarDB(int dinero_click, int dinero_segundo, long dinero_total, String usuario, String contraseña, int cooldown){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO usuario VALUES ('%s', %d, %d, '%s', %d, %d)", usuario, dinero_total, dinero_click, contraseña, dinero_segundo, cooldown);
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}
}
