package clases;

import java.io.Serializable;

public class Edificios extends ElementoJuego implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -543235933317680342L;
	long eCantidad;
	int eProduccion;
	String eImg;
	
	
	public Edificios( String nombre, long precio, long cant, int prod, String image) {
		super(precio, nombre);
		this.eCantidad=cant;
		this.eProduccion=prod;
		this.eImg=image;
	}

	

	public long geteCantidad() {
		return eCantidad;
	}

	public void seteCantidad(long eCantidad) {
		this.eCantidad = eCantidad;
	}

	public long geteProduccion() {
		return eProduccion;
	}

	public void seteProduccion(int eProduccion) {
		this.eProduccion = eProduccion;
	}

	public String geteImg() {
		return eImg;
	}

	public void seteImg(String eImg) {
		this.eImg = eImg;
	}
	public long incrementar(int num) {
		long total = this.geteCantidad()+1;
		return total;
		
	}

	@Override
	public String toString() {
		return this.getNombre()+"("+this.getPrecio()+"$)"+" // "+this.geteProduccion()+"$/s // "+this.geteCantidad()+" Obtenidos";
	}
	
}
