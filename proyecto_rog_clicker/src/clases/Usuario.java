package clases;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075924453208342684L;
	String nombre;
	String apellido;
	String nUsuario;
	String contraseña;
	public Usuario(String nombre, String apellido, String nUsuario, String contraseña) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nUsuario = nUsuario;
		this.contraseña = contraseña;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getnUsuario() {
		return nUsuario;
	}
	public void setnUsuario(String nUsuario) {
		this.nUsuario = nUsuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", nUsuario=" + nUsuario + ", contraseña="
				+ contraseña + "]";
	}
	
}
