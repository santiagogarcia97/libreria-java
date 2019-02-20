package libreria.controllers;

import libreria.data.DataEjemplar;
import libreria.entities.Ejemplar;
import libreria.entities.Libro;

public class CtrlEjemplar {

	private DataEjemplar dataEjemplar;
	
	public CtrlEjemplar() {
		dataEjemplar = new DataEjemplar();
	}
	
	public Ejemplar getOneByLibro(Libro l) {
		Ejemplar e = dataEjemplar.getOneByLibro(l);
		if(e != null) return e;
		else {
			e = new Ejemplar();
			e.setId(-1); 
			return e;
		}
	}
	
	
}
