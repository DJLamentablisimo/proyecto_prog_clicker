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
	public void efecto(int boost_clicks, int boost_prod, int boost_cant, long produccion, long clicks, long cant, long tiempo) {
		produccion += boost_prod;
		clicks += boost_clicks;
		cant += boost_cant;
		
	}

	
	
	
	
	
	
	

}
