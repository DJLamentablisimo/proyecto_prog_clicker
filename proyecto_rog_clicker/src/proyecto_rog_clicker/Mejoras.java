package proyecto_rog_clicker;

import java.io.Serializable;

public class Mejoras extends ElementoJuego implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long incremento;
	String mImg;
	
	public Mejoras(String nombre, long precio, long incremento, String img) {
		super(precio,nombre);
		this.precio = precio;
		this.nombre = nombre;
		this.incremento = incremento;
		this.mImg = img;
	
	}

	public long getIncremento() {
		return incremento;
	}

	public void setIncremento(long incremento) {
		this.incremento = incremento;
	}

	public String getmImg() {
		return mImg;
	}

	public void setmImg(String mImg) {
		this.mImg = mImg;
	}

	
	
	
	
	
	
	

}
