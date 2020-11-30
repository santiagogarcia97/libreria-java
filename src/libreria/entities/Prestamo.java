package libreria.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import libreria.data.DataEjemplar;
import libreria.data.DataLibro;
import libreria.utils.CustomException;

public class Prestamo extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private int socioId;
	private Date fechaHoraSolicitud, fechaHoraPreparacion, fechaHoraRetiro,fechaHoraDevolucion;

	ArrayList<LineaDePrestamo> lineas = new ArrayList<LineaDePrestamo>();
	
	public Prestamo() {}
	
	////////////
	// Setters
	////////////
	public void setSocioId(int socioId) {
		this.socioId = socioId;
	}
	public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
		this.fechaHoraSolicitud = fechaHoraSolicitud;
	}
	public void setFechaHoraPreparacion(Date fechaHoraPreparacion) {
		this.fechaHoraPreparacion = fechaHoraPreparacion;
	}
	public void setFechaHoraRetiro(Date fechaHoraRetiro) {
		this.fechaHoraRetiro = fechaHoraRetiro;
	}	
	public void setFechaHoraDevolucion(Date fechaHoraDevolucion) {
		this.fechaHoraDevolucion = fechaHoraDevolucion;
	}	
	
	public void addLinea(LineaDePrestamo lp) {
		this.lineas.add(lp);
	}
	
	
	////////////
	// Getters
	////////////	
	public int getSocioId() {
		return socioId;
	}
	public Date getFechaHoraSolicitud() {
		return fechaHoraSolicitud;
	}
	public Date getFechaHoraPreparacion() {
		return fechaHoraPreparacion;
	}
	public Date getFechaHoraRetiro() {
		return fechaHoraRetiro;
	}
	public Date getFechaHoraDevolucion() {
		return fechaHoraDevolucion;
	}

	public ArrayList<LineaDePrestamo> getLineas(){
		return this.lineas;
	}
	
	public LocalDate calc_fecha_devolver() throws CustomException {
		// Calcula y devuelve fecha maxima en la que debe ser devuelto el prestamo
		DataEjemplar de = new DataEjemplar();
		DataLibro dl = new DataLibro();
		
		if(this.getFechaHoraRetiro() == null) {
			//TODO: throw CustomException
		}
		
		LocalDate fechaRetiro = this.getFechaHoraRetiro().toLocalDate();
		ArrayList<Integer> diasPrestamo = new ArrayList<Integer>();
		
		for(LineaDePrestamo lp : this.getLineas()) {
			Ejemplar ej = lp.getEjemplar();
			ej = de.getById(ej);
			Libro l = ej.getLibro();
			l = dl.getById(l);
			diasPrestamo.add(l.getDiasMaxPrestamo());
		}
		
		Integer min = Collections.min(diasPrestamo);
		return fechaRetiro.plusDays(min);
	}
	
	public int calc_dias_restantes() {
		LocalDate fecha = this.calc_fecha_devolver();
		return (int) LocalDate.now().until(fecha, ChronoUnit.DAYS);
	}
	
}
