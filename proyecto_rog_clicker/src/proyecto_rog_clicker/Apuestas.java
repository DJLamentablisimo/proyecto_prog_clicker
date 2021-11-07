package proyecto_rog_clicker;

import java.io.Serializable;

public class Apuestas extends ElementoJuego implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long aincremento;
	String aimg;
	long tiempo;

	
	
	
	public Apuestas( String nombre, long precio, long incremento, String img ) {
		super(precio, nombre);
		this.precio = precio;
		this.nombre = nombre;
		this.aincremento = incremento;
		this.aimg = img;
		
	}


	public long getAincremento() {
		return aincremento;
	}


	public void setAincremento(long aincremento) {
		this.aincremento = aincremento;
	}


	public String getAimg() {
		return aimg;
	}


	public void setAimg(String aimg) {
		this.aimg = aimg;
	}


	public long getTiempo() {
		return tiempo;
	}


	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
	
	
	@Override
	public String toString() {
		return "Apuestas [aincremento=" + aincremento + ", aimg=" + aimg + ", tiempo=" + tiempo + "]";
	}



	@Override
	public long efecto(long boost,long cant) {
		int valorRNG = (int) Math.floor(Math.random()*2);
		System.out.println(valorRNG);
		
		if(valorRNG==0) {
			return cant += boost;
		}
		
		if(valorRNG==1) {
			if((cant - boost)<=0) {
				return cant = 0;
			} else if((cant - boost)>0) {
				return cant -= boost;
			}
			
		}
		
		return 69;
	}
}
