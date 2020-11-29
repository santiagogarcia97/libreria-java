package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataSancion;
import libreria.entities.Sancion;
import libreria.utils.CustomException;

public class CtrlSancion {
	
	private DataSancion dataS;	
	
	public CtrlSancion(){
		dataS = new DataSancion();
	}
	
	public Sancion add(Sancion s) throws CustomException{
		return this.dataS.add(s);
	}
	
	public Sancion getById(Sancion s) throws CustomException{
		return this.dataS.getById(s);
	}	
	
	public ArrayList<Sancion> getAll() throws CustomException{
		return this.dataS.getAll();
	}
	
	public ArrayList<Sancion> getBySocio(int id) throws CustomException{
		return this.dataS.getBySocio(id);
	}
	
	public void update(Sancion s) throws CustomException{
		this.dataS.update(s);
	}
	public void delete(Sancion s) throws CustomException{
		this.dataS.delete(s);
	}

}
