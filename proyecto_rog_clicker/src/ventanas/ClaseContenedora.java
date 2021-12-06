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
import org.json.simple.JSONObject;    



public class ClaseContenedora {
	private static Logger logger = Logger.getLogger("ClaseContenedora");

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// MODIFICACIÓN DE LA APUESTA 2 CON SU EVENTO DE RATON ////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA CAMBIAR LOS ATRIBUTOS DEL USUARIO DE LA BD/////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actualizarPartida(String usuario, long a, int b, int c){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("UPDATE usuario SET dinero_total = "+ a +", dinero_click= "+ b +", dinero_por_segundo = "+ c +" where nombre_usuario = '"+ usuario+"';");
			logger.log(Level.INFO, "Statement: " + sql);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA SACAR EL ARRAYLIST CON LOS USUARIOS DE LA BD //////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<Edificios> sacarEdificios(){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			ArrayList<Edificios> dev = new ArrayList<>();
			String sql = "SELECT * FROM edificios";
			logger.log(Level.INFO, "Statement: " + sql);
			ResultSet rs = stmt.executeQuery("SELECT * FROM edificios");
			while( rs.next() ) { 
				String nombre = rs.getString("nombre");
				long precio = rs.getLong("precio");
				long cant = rs.getInt("eCantidad");
				int prod = rs.getInt("eProduccion");

				dev.add( new Edificios( nombre, precio, cant, prod, "") );
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA METER UN USUARIO EN LA BASE DE DATOS //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void guardarDBUsuario(int dinero_click, int dinero_segundo, long dinero_total, String usuario, String contraseña, int cooldown){
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO usuario VALUES ('%s', %d, %d, '%s', %d, %d)", usuario, dinero_total, dinero_click, contraseña, dinero_segundo, cooldown);
			logger.log(Level.INFO, "Statement: " + sql);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA BORRAR UN USUARIO DE LA BASE DE DATOS /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void borrarDBUsuario(String usuario){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM usuario where nombre_usuario = '"+ usuario+"';");
			logger.log(Level.INFO, "Statement: " + sql);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA CAMBIAR LA CONTRASEÑA DEL USUARIO SELECCIONADO EN LA BD ///////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void cambiarContraseñaBD(String usuario, String contraseña, String nuevaContra){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("UPDATE usuario SET contraseña = '"+nuevaContra+"' where nombre_usuario = '"+ usuario+"' AND contraseña= '"+contraseña+"';");
			logger.log(Level.INFO, "Statement: " + sql);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA METER UN NUEVO EDIFICIO EN LA BD //////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void guardarDBEdificio(String nombre, long precio, long cant, int prod, String image ){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO edificios VALUES ('%s', %d, %d, %d, '%s')", nombre, precio, cant, prod, image);
			logger.log(Level.INFO, "Statement: " + sql);
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM edificios");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA BORRAR UN EDIFICIO DE LA BD ///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void borrarDBEdificio(String nombre){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM edificios where nombre = '"+ nombre+"';");
			logger.log(Level.INFO, "Statement: " + sql);
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM edificios");
			stmt2.close();
			rs.close();
			conn.close(); 
			} catch (SQLException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
			}
		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA AÑADIR MEJORAS EN LA BD ///////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void guardarMejoras( long precio, String nombre, long incremento, String img){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO mejoras VALUES ( %d,'%s', %d, '%s')", precio, nombre, incremento, img);
			logger.log(Level.INFO, "Statement: " + sql);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////// FUNCION PARA BORRAR MEJORA DE LA BD ////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void borrarMejoras(String nombre){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Usuario.db");
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM mejoras where nombre = '"+ nombre+"';");
			logger.log(Level.INFO, "Statement: " + sql);
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
