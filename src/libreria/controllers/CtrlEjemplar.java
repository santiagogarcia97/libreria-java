package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataEjemplar;
import libreria.entities.Categoria;
import libreria.entities.Ejemplar;
import libreria.entities.Libro;
import libreria.utils.CustomException;

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
	
	public ArrayList<Ejemplar> getAll() throws CustomException{
		return this.dataEjemplar.getAll();
	}
	
	public Integer getCountByLibro(Libro l) {
		return this.dataEjemplar.getCountByLibro(l);
	}
	
	public Ejemplar add(Ejemplar e) throws CustomException{
		return this.dataEjemplar.add(e);
	}
	
	public void delete(Ejemplar e) throws CustomException{
		this.dataEjemplar.delete(e);
	}
	
	public void update(Ejemplar e) throws CustomException{
		this.dataEjemplar.update(e);
	}
	
}
