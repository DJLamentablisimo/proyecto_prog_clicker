package clases;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075924453208342684L;
	String nUsuario;
	String contraseña;
	long dinero_total_personal;
	int dinero_por_segundo_personal;  
	int dinero_click_personal;
	public Usuario(String nUsuario, String contraseña, long dtp, int dpsp, int dcp) {
		super();
		this.nUsuario = nUsuario;
		this.contraseña = contraseña;
		this.dinero_click_personal=dcp;
		this.dinero_por_segundo_personal=dpsp;
		this.dinero_total_personal=dtp;
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
	
	public long getDinero_total_personal() {
		return dinero_total_personal;
	}

	public void setDinero_total_personal(long dinero_total_personal) {
		this.dinero_total_personal = dinero_total_personal;
	}

	public int getDinero_por_segundo_personal() {
		return dinero_por_segundo_personal;
	}

	public void setDinero_por_segundo_personal(int dinero_por_segundo_personal) {
		this.dinero_por_segundo_personal = dinero_por_segundo_personal;
	}

	public int getDinero_click_personal() {
		return dinero_click_personal;
	}

	public void setDinero_click_personal(int dinero_click_personal) {
		this.dinero_click_personal = dinero_click_personal;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nUsuario + ", contraseña="
				+ contraseña + "]";
	}
	
}
