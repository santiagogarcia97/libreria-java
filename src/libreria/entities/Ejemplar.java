package libreria.entities;

public class Ejemplar extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private boolean disponible;
	private Libro libro;
	
	public Ejemplar() {}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}

