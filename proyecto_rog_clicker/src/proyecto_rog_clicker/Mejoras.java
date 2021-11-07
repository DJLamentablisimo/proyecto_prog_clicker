package proyecto_rog_clicker;

import java.io.Serializable;

public class Mejoras extends ElementoJuego implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long mincremento;
	String mImg;
	
	public Mejoras(String nombre, long precio, long incremento, String img) {
		super(precio,nombre);
		this.precio = precio;
		this.nombre = nombre;
		this.mincremento = incremento;
		this.mImg = img;
	
	}

	public long getIncremento() {
		return mincremento;
	}

	public void setIncremento(long incremento) {
		this.mincremento = incremento;
	}

	public String getmImg() {
		return mImg;
	}

	public void setmImg(String mImg) {
		this.mImg = mImg;
	}
	
	@Override
	public long efecto(long boost, long cant) {
		cant += boost;
		return 0;
	}

	
	
	
	
	
	
	

}
