package libreria.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

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
	
}
