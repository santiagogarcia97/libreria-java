package libreria.entities;
import java.time.LocalDate;;


public class LineaDePrestamo extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private boolean devuelto;
	private LocalDate fechaDevolucion;
	private Ejemplar ejemplar;
	
	
	public LineaDePrestamo() {}
	
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	
	public void setFechaDevolucion(LocalDate fecha) {
		this.fechaDevolucion = fecha;
	}
	
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public LocalDate getFechaDevolucion() {
		return this.fechaDevolucion;
	}
	
	public boolean isDevuelto() {
		return this.devuelto;
	}
	
	public Ejemplar getEjemplar(){
		return this.ejemplar;
	}
}
