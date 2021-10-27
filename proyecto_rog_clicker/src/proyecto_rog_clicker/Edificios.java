package proyecto_rog_clicker;

public class Edificios {
	String eNombre;
	int eCoste;
	int eCantidad;
	int eProduccion;
	String eImg;
	
	
	public Edificios(String nombre, int coste, int cant, int prod, String image) {
		this.eNombre=nombre;
		this.eCoste=coste;
		this.eCantidad=cant;
		this.eProduccion=prod;
		this.eImg=image;
	}

	public String geteNombre() {
		return eNombre;
	}

	public void seteNombre(String eNombre) {
		this.eNombre = eNombre;
	}

	public int geteCoste() {
		return eCoste;
	}

	public void seteCoste(int eCoste) {
		this.eCoste = eCoste;
	}

	public int geteCantidad() {
		return eCantidad;
	}

	public void seteCantidad(int eCantidad) {
		this.eCantidad = eCantidad;
	}

	public int geteProduccion() {
		int suma = eCantidad*eProduccion;
		return suma;
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
	@Override
	public String toString() {
		return "Edificio: - "+this.eNombre+"("+this.eCoste+") - ["+this.eCantidad+" comprados -- "+this.eProduccion +" dineros/seg]"+ " . . . Producido hasta ahora: "+geteProduccion()+" dineros";
	}
	public static void main(String[] args) {
		Edificios e1 = new Edificios("Banco", 300, 4, 5, "Imagen");
		System.out.println(e1);
	}
}
