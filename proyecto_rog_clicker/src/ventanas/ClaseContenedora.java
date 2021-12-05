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
import clases.Edificios;

public class ClaseContenedora {
	private static Logger logger = Logger.getLogger("ClaseContenedora");

	
	
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
		//claseCont.guardarMejoras(2, "Odisea",  10, "kaka/img");	
		//claseCont.borrarMejoras("Odisea");
		/*claseCont.guardarDBEdificio("Edificio1", 120, 0, 1, "");
		claseCont.guardarDBEdificio("Edificio2", 200, 0, 2, "");
		claseCont.guardarDBEdificio("Edificio3", 250, 0, 3, "");
		claseCont.guardarDBEdificio("Edificio4", 550, 0, 4, "");
		claseCont.guardarDBEdificio("Edificio5", 1000, 0, 5, "");
		claseCont.guardarDBEdificio("Edificio6", 2300, 0, 6, "");
		claseCont.guardarDBEdificio("Edificio7", 5000, 0, 7, "");
		claseCont.guardarDBEdificio("Edificio8", 7000, 0, 8, "");
		claseCont.guardarDBEdificio("Edificio9", 10000, 0, 9, "");
		claseCont.guardarDBEdificio("Edificio10", 20000, 0, 10, "");
		claseCont.guardarDBEdificio("Edificio11", 25000, 0, 11, "");
		claseCont.guardarDBEdificio("Edificio12", 30000, 0, 12, "");
		claseCont.guardarDBEdificio("Edificio13", 40000, 0, 13, "");
		claseCont.guardarDBEdificio("Edificio14", 50000, 0, 14, "");
		claseCont.guardarDBEdificio("Edificio15", 75000, 0, 15, "");
		claseCont.guardarDBEdificio("Edificio16", 100000, 0, 16, "");
		claseCont.guardarDBEdificio("Edificio17", 500000, 0, 17, "");
		*/
		System.out.println(claseCont.sacarUsuarios());
	}
	public ArrayList<Usuario> sacarUsuarios(){
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
	public ArrayList<Edificios> sacarEdificios(){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			ArrayList<Edificios> dev = new ArrayList<>();
			String sql = "SELECT * FROM edificio";
			logger.log(Level.INFO, "Statement: " + sql);
			ResultSet rs = stmt.executeQuery("SELECT * FROM edificio");
			while( rs.next() ) { 
				String nombre = rs.getString("nombre");
				long precio = rs.getLong("precio");
				long cant = rs.getInt("eCantidad");
				int prod = rs.getInt("eProduccion");
				String image = rs.getString("eImg");
				dev.add( new Edificios( nombre, precio, cant, prod, image ) );
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
	
	public void guardarDBUsuario(int dinero_click, int dinero_segundo, long dinero_total, String usuario, String contraseña, int cooldown){
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
	public void borrarDBUsuario(String usuario){
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
	public void guardarDBEdificio(String nombre, long precio, long cant, int prod, String image ){
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
	public void borrarDBEdificio(String nombre){
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
	public void guardarMejoras( long precio, String nombre, long incremento, String img){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO mejoras VALUES ( %d,'%s', %d, '%s')", precio, nombre, incremento, img);
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mejoras");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}
	
	public void borrarMejoras(String nombre){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM mejoras where nombre = '"+ nombre+"';");
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mejoras");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}
}
