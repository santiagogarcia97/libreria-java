package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataLibro;
import libreria.entities.Categoria;
import libreria.entities.Libro;
import libreria.utils.CustomException;

public class CtrlLibro {
	
	private DataLibro dataLibro;	
	
	public CtrlLibro(){
		dataLibro = new DataLibro();
	}
	
	public Libro add(Libro l) throws CustomException{
		return this.dataLibro.add(l);
	}
	
	public Libro getById(Libro l) throws CustomException{
		return this.dataLibro.getById(l);
	}	
	
	public ArrayList<Libro> getAll() throws CustomException{
		return dataLibro.getAll();
	}
	public ArrayList<Libro> getByCat(Categoria c) throws CustomException{
		return dataLibro.getByCat(c);
	}
	public void update(Libro l) throws CustomException{
		this.dataLibro.update(l);
	}
	public void delete(Libro l) throws CustomException{
		this.dataLibro.delete(l);
	}

}
