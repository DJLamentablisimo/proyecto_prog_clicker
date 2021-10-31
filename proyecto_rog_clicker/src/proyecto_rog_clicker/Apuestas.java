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

	
	
	
	public Apuestas( String nombre, long precio,long tiempo, long incremento, String img ) {
		super(precio, nombre);
		this.precio = precio;
		this.nombre = nombre;
		this.tiempo = tiempo;
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
	
	//TODO método para gestionar tiempo 
	@Override
	public void efecto(int boost_clicks, int boost_prod, int boost_cant, long produccion, long clicks, long cant, long tiempo) {
		while(tiempo!=0) {
		produccion += boost_prod;
		}
		while(tiempo!=0) {
		clicks += boost_clicks;
		}
		cant += boost_cant;
		
	}
}
