package libreria.entities;
import java.sql.Date;
import java.time.*;


public class LineaDePrestamo extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private boolean devuelto;
	private Date fechaDevolucion;
	private Ejemplar ejemplar;
	private int idPrestamo;
	
	
	public LineaDePrestamo() {}
	
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	
	public void setFechaDevolucion(Date fecha) {
		this.fechaDevolucion = fecha;
	}
	
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}
	
	public void setIdPrestamo(int id) {
		this.idPrestamo = id;
	}
	
	public Date getFechaDevolucion() {
		return this.fechaDevolucion;
	}
	
	public boolean isDevuelto() {
		return this.devuelto;
	}
	
	public Ejemplar getEjemplar(){
		return this.ejemplar;
	}
	
	public int getIdPrestamo() {
		return this.idPrestamo;
	}
}
