package libreria.entities;
package java.time;


public class Sancion extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private int diasSancion;
	private LocalDate fechaSancion;
	
	
	public Sancion() {}
	
	public void setDiasSancion(int dias) {
		this.diasSancion = dias;
	}
	
	public void setFechaSancion(LocalDate fecha) {
		this.fechaSancion = fecha;
	}
	

	public LocalDate getFechaSancion() {
		return this.fechaSancion;
	}
	
	public int getDiasSancion() {
		return this.diasSancion;
	}
	


