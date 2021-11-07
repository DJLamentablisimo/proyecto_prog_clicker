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
	
	//TODO método para gestionar tiempo 
	@Override
	public void efecto(long boost_clicks, long boost_prod, long boost_cant, long produccion, long clicks, long cant) {
		int valorRNG = (int) Math.floor(Math.random()*2);
		
		if(valorRNG==0) {
			produccion += boost_prod;		
			clicks += boost_clicks;		
			cant += boost_cant;
		}
		
		if(valorRNG==1) {
			if((produccion - boost_prod)<=0) {
				produccion = 0;
			} else if((produccion - boost_prod)>0) {
				produccion -= boost_prod;
			}
			
			if((clicks - boost_clicks)<=0) {
				clicks = 0;
			} else if((clicks - boost_clicks)>0) {
				clicks -= boost_clicks;
			}
			
			if((cant - boost_cant)<=0) {
				cant = 0;
			} else if((cant - boost_cant)>0) {
				cant -= boost_cant;
			}
			
		}
	}
}
