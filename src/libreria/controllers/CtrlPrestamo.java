package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataLineaDePrestamo;
import libreria.data.DataPrestamo;
import libreria.entities.LineaDePrestamo;
import libreria.entities.Prestamo;

public class CtrlPrestamo {

	private DataPrestamo dataPrestamo;
	private DataLineaDePrestamo dataLinea;
	
	public CtrlPrestamo() {
		dataPrestamo = new DataPrestamo();
		dataLinea = new DataLineaDePrestamo();
	}
	
	public void add(Prestamo p) {
		p = dataPrestamo.add(p);
		
		ArrayList<LineaDePrestamo> lineas = p.getLineas();
		for(LineaDePrestamo lp : lineas) {
			lp.setIdPrestamo(p.getId());
			dataLinea.add(lp);
		}
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
	
}
