import java.util.*;  

package libreria.entities;
package java.time;


public class LineaDePrestamo extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private boolean devuelto;
	private LocalDate fechaDevolucion;
	private ArrayList<Ejemplar> ejemplares=new ArrayList<Ejemplar>();
	
	
	public LineaDePrestamo() {}
	
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	
	public void fechaDevolucion(LocalDate fecha) {
		this.fechaDevolucion = fecha;
	}
	
	public void addEjemplar(Ejemplar ejemplar) {
		this.ejemplares.add(ejemplar);
	}

	public LocalDate getFechaDevolucion() {
		return this.fechaDevolucion;
	}
	
	public boolean isDevuelto() {
		return this.devuelto;
	}
	
	public ArrayList<Ejemplar> getEjemplares(){
		return this.ejemplares;
	}

