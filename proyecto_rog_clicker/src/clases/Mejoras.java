package clases;

import java.io.Serializable;

public class Mejoras extends ElementoJuego implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long mincrementodps;
	long mincrementodc;
	long mincrementodt;
	
	String mImg;
	
	public Mejoras(String nombre, long precio, long incrementodps, long incrementodc, long incrementodt, String img) {
		super(precio,nombre);
		this.precio = precio;
		this.nombre = nombre;
		this.mincrementodps = incrementodps;
		this.mincrementodc = incrementodc;
		this.mincrementodt = incrementodt;
		this.mImg = img;
	
	}

	public long getIncrementoDps() {
		return mincrementodps;
	}

	public void setIncrementoDps(long incrementodps) {
		this.mincrementodps = incrementodps;
	}
	
	public long getIncrementoDc() {
		return mincrementodc;
	}

	public void setIncrementoDc(long incrementodc) {
		this.mincrementodc = incrementodc;
	}
	
	public long getIncrementoDt() {
		return mincrementodt;
	}

	public void setIncrementoDt(long incrementodt) {
		this.mincrementodt = incrementodt;
	}

	public String getmImg() {
		return mImg;
	}

	public void setmImg(String mImg) {
		this.mImg = mImg;
	}
	
	@Override
	public long efecto(long boost, long cant) {
		cant = cant*boost;
		return cant;
	}

	
	

}
