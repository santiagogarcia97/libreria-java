package libreria.controllers;

import java.util.ArrayList;

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
			this.dataPrestamo.update(p);
			return s;
		}
		else if(p.getEstado().equals("retiro")) {
			String s = "devolucion";
			p.setEstado(s);
			this.dataPrestamo.update(p);
			return s;
		}
		else if(p.getEstado().equals("devolucion")) {
			this.delete(id);
		}
		return "error-"+p.getEstado();
	}
}
