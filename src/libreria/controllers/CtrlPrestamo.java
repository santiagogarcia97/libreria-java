package libreria.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import libreria.data.DataEjemplar;
import libreria.data.DataLineaDePrestamo;
import libreria.data.DataPrestamo;
import libreria.entities.Ejemplar;
import libreria.entities.Libro;
import libreria.entities.LineaDePrestamo;
import libreria.entities.Prestamo;
import libreria.utils.CustomException;

public class CtrlPrestamo {

	private DataPrestamo dataPrestamo;
	private DataLineaDePrestamo dataLinea;
	
	public CtrlPrestamo() {
		dataPrestamo = new DataPrestamo();
		dataLinea = new DataLineaDePrestamo();
	}
	
	public ArrayList<Prestamo> getAll() throws CustomException{
		return dataPrestamo.getAll();
	}
	
	public Prestamo add(Prestamo p) {
		p = dataPrestamo.add(p);
		
		ArrayList<LineaDePrestamo> lineas = (ArrayList<LineaDePrestamo>) p.getLineas();
		for(LineaDePrestamo lp : lineas) {
			lp.setIdPrestamo(p.getId());
			dataLinea.add(lp);
		}
		return p;
	}
	
	public void eliminarLinea(int id) {
		LineaDePrestamo lp = new LineaDePrestamo();
		Ejemplar ej = new Ejemplar();
		DataEjemplar dataEjemplar = new DataEjemplar();
		lp.setId(id);
		lp = dataLinea.getById(lp);
		ej = lp.getEjemplar();
		ej = dataEjemplar.getById(ej);
		ej.setEstado("habilitado");
		dataEjemplar.update(ej);
		dataLinea.delete(lp);
	}
	
	public void agregarLinea(int id, int idP) {
		DataEjemplar dataEjemplar = new DataEjemplar();
		Ejemplar ej = new Ejemplar();
		ej.setId(id);
		ej = dataEjemplar.getById(ej);
		ej.setEstado("deshabilitado");
		dataEjemplar.update(ej);
		
		LineaDePrestamo lp = new LineaDePrestamo();
		lp.setEjemplar(ej);
		lp.setIdPrestamo(idP);
		lp.setEstado("preparacion");
		this.dataLinea.add(lp);
		
	}
	
	public void delete(int id) {
		Prestamo p = new Prestamo();
		DataEjemplar de = new DataEjemplar();
		p.setId(id);
		p = this.dataPrestamo.getById(p);
		for(LineaDePrestamo lp : p.getLineas()) {
			Ejemplar ej = lp.getEjemplar();
			ej = de.getById(ej);
			ej.setEstado("habilitado");
			de.update(ej);
			
			this.dataLinea.delete(lp);
		}
		this.dataPrestamo.delete(p);
	}
	public int countPrestamosPreparacion() {
		return dataPrestamo.countPreparacion();
	}
	
	public int countPrestamosRetiro() {
		return dataPrestamo.countRetiro();
	}
	public int countPrestamosDevolucion() {
		return dataPrestamo.countDevolucion();
	}
	
	public String confirmarPrestamo(int id) {
		Prestamo p = new Prestamo();
		p.setId(id);
		p = this.dataPrestamo.getById(p);
		
		if(p.getEstado().equals("preparacion")) {
			String s = "retiro";
			p.setEstado(s);
			p.setFechaHoraPreparacion(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			this.dataPrestamo.update(p);
			return s;
		}
		else if(p.getEstado().equals("retiro")) {
			String s = "devolucion";
			p.setEstado(s);
			p.setFechaHoraRetiro(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			this.dataPrestamo.update(p);
			return s;
		}
		else if(p.getEstado().equals("devolucion")) {
			p.setFechaHoraDevolucion(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			this.delete(id);
		}
		return "error-"+p.getEstado();
	}
	
	public LocalDate calc_fecha_devolver(int id) throws CustomException {
		// Calcula y devuelve fecha maxima en la que debe ser devuelto el prestamo
		DataEjemplar de = new DataEjemplar();
		Prestamo p = new Prestamo();
		p.setId(id);
		p = this.dataPrestamo.getById(p);
		
		if(p.getFechaHoraRetiro() == null) {
			//TODO: throw CustomException
		}
		
		LocalDate fechaRetiro = p.getFechaHoraRetiro().toLocalDate();
		ArrayList<Integer> diasPrestamo = new ArrayList<Integer>();
		
		for(LineaDePrestamo lp : p.getLineas()) {
			Ejemplar ej = lp.getEjemplar();
			ej = de.getById(ej);
			diasPrestamo.add(ej.getLibro().getDiasMaxPrestamo());
		}
		
		Integer min = Collections.min(diasPrestamo);
		
		return fechaRetiro.plusDays(min);
	}
	
	public int calc_dias_restantes(int id) {
		LocalDate fecha = this.calc_fecha_devolver(id);
		return (int) LocalDate.now().until(fecha, ChronoUnit.DAYS);
	}
}

