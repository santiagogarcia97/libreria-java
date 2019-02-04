package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataCategoria;
import libreria.entities.Categoria;
import libreria.utils.CustomException;

public class CtrlCategoria {
	
	private DataCategoria dataCat;	
	
	public CtrlCategoria(){
		dataCat = new DataCategoria();
	}
	
	public Categoria add(Categoria c) throws CustomException{
		return this.dataCat.add(c);
	}
	
	public Categoria getById(Categoria c) throws CustomException{
		return this.dataCat.getById(c);
	}	
	
	public ArrayList<Categoria> getAll() throws CustomException{
		return this.dataCat.getAll();
	}
	public void update(Categoria c) throws CustomException{
		this.dataCat.update(c);
	}
	public void delete(Categoria c) throws CustomException{
		this.dataCat.delete(c);
	}

}
