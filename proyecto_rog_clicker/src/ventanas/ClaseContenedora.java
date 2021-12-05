package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import clases.Usuario;

public class ClaseContenedora {
	private static Logger logger = Logger.getLogger("ClaseContenedora");

	static int cooldown;
	static int dinero_click=VentanaUsuario.usuarioActual.getDinero_click_personal();
	static int dinero_por_segundo=VentanaUsuario.usuarioActual.getDinero_por_segundo_personal();
	//static long dinero_total = VentanaUsuario.usuarioActual.getDinero_total_personal();
	static long dinero_total = 5000000;
	
	public static void main(String[] args) {
		ClaseContenedora claseCont = new ClaseContenedora();

		/*claseCont.guardarDBUsuario(1, 1, 100, "JorgeAlonsoSuS", "pepelotas1", 3200);
		claseCont.guardarDBUsuario(2, 2, 200, "DavidBarrenechea", "pepelotas2", 3200);
		claseCont.guardarDBUsuario(1, 2, 432, "DjLamentablisimo", "pepelotas3", 3200);
		claseCont.guardarDBUsuario(25, 1, 500, "BellosoAsier", "pepelotas4", 3200);
		*/
		/*claseCont.guardarDBEdificio("Edificio1", 200, 7, 4, "imagen 1");
		claseCont.guardarDBEdificio("Edificio2", 500, 3, 2, "imagen 2");
		claseCont.guardarDBEdificio("Edificio3", 2300, 23, 34, "imagen 3");
		claseCont.guardarDBEdificio("Edificio4", 67800, 34, 3, "imagen 4");
		claseCont.guardarDBEdificio("Edificio5", 7600, 34, 2, "imagen 5");
		claseCont.guardarDBEdificio("Edificio6", 12300, 34, 3, "imagen 6");
		claseCont.guardarDBEdificio("Edificio7", 64300, 3, 67, "imagen 7");
		claseCont.guardarDBEdificio("Edificio8", 900, 1, 93, "imagen 8");
		*/
		/*claseCont.borrarDBEdificio("Edificio7");
		*/
		System.out.println(claseCont.sacarUsuarios());
	}
	private ArrayList<Usuario> sacarUsuarios(){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			ArrayList<Usuario> dev = new ArrayList<>();
			String sql = "SELECT * FROM usuario";
			logger.log(Level.INFO, "Statement: " + sql);
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while( rs.next() ) { 
				String nombre = rs.getString("nombre_usuario");
				long dinerototal = rs.getLong("dinero_total");
				int dineroclick = rs.getInt("dinero_click");
				String contraseña = rs.getString("contraseña");
				int dinero_por_segundo = rs.getInt("dinero_por_segundo");
				dev.add( new Usuario( nombre, contraseña, dinerototal, dinero_por_segundo, dineroclick ) );
			}
			rs.close();
			stmt.close();
			conn.close(); 
			return dev;
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
			return null;
		
	}
	private void guardarDBUsuario(int dinero_click, int dinero_segundo, long dinero_total, String usuario, String contraseña, int cooldown){
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
	private void borrarDBUsuario(String usuario){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM usuario where nombre_usuario = '"+ usuario+"';");
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
	private void cambiarContraseñaBD(String usuario, String contraseña, String nuevaContra){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("UPDATE usuario SET contraseña = '"+nuevaContra+"' where nombre_usuario = '"+ usuario+"' AND contraseña= '"+contraseña+"';");
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
	private void guardarDBEdificio(String nombre, long precio, long cant, int prod, String image ){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO edificio VALUES ('%s', %d, %d, %d, '%s')", nombre, precio, cant, prod, image);
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM edificio");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}
	private void borrarDBEdificio(String nombre){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM edificio where nombre = '"+ nombre+"';");
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM edificio");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}
}
