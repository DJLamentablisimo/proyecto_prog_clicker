package proyecto_rog_clicker;

public class ElementoJuego {
	protected long precio;
	protected String nombre;
	
	public ElementoJuego(long precio, String nombre) {
		this.precio = precio;
		this.nombre = nombre;
	}
	
	public long efecto(long boost, long cant) {
		return 0;
		
	}

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
