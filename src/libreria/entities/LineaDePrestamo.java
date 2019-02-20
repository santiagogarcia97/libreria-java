package libreria.entities;
import java.sql.Date;


public class LineaDePrestamo extends Entidad {
	
	private static final long serialVersionUID = 1L;
	

	private Date fechaDevolucion;
	private Ejemplar ejemplar;
	private int idPrestamo;
	
	
	public LineaDePrestamo() {}

	////////////
	// Setters
	////////////
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}	
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}	
	
	////////////
	// Getters
	////////////	
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public Ejemplar getEjemplar() {
		return ejemplar;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}	

}
