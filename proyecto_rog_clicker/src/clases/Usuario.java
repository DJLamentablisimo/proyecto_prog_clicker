package clases;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075924453208342684L;
	String nUsuario;
	String contraseña;
	public Usuario(String nombre, String apellido, String nUsuario, String contraseña) {
		super();
		this.nUsuario = nUsuario;
		this.contraseña = contraseña;
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
		return "Usuario [nombre=" + nUsuario + ", contraseña="
				+ contraseña + "]";
	}
	
}
