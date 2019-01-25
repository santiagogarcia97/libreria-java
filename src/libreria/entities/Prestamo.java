package libreria.entities;

import java.time.*;
import java.util.ArrayList;

public class Prestamo extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime fechaHoraPrestamo;
	private LocalDate fechaADevolver;
	private int diasPrestamo;
	ArrayList<LineaDePrestamo> lineas = new ArrayList<LineaDePrestamo>();
	
	public Prestamo() {}
	
	public void setFechaHoraPrestamo(LocalDateTime fechaHora) {
		this.fechaHoraPrestamo = fechaHora;
	}
	
	public void setFechaADevolver(LocalDate fecha) {
		this.fechaADevolver = fecha;
	}
	
	public void setDiasPrestamo(int dias) {
		this.diasPrestamo = dias;
	}
	
	public void addLinea(LineaDePrestamo linea) {
		this.lineas.add(linea);
	}
	

	public LocalDateTime getFechaHoraPrestamo() {
		return this.fechaHoraPrestamo;
	}
	
	public LocalDate getFechaADevolver() {
		return this.fechaADevolver;
	}
	
	public int getDiasPrestamo() {
		return this.diasPrestamo;
	}
	
	public ArrayList<LineaDePrestamo> getLineasDePrestamo() {
		return this.lineas;
	}
}
