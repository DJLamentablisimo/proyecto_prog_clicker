package clases;

public class mejorasPorUsuario {
	
	private String codUsuario;
	private String codMejora;
	private int cantidad;

	public mejorasPorUsuario(String codUsuario, String codMejora, int cantidad) {
		this.codUsuario = codUsuario;
		this.codMejora = codMejora;
		this.cantidad = cantidad;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getCodMejora() {
		return codMejora;
	}

	public void setCodMejora(String codMejora) {
		this.codMejora = codMejora;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
