package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataSocio;
import libreria.entities.Socio;
import libreria.utils.BCrypt;

public class CtrlSocio {
	
	private DataSocio dataSocio;	
	
	public CtrlSocio(){
		dataSocio = new DataSocio();
	}
	
	public void add(Socio s) throws Exception{
		String hashedPass = BCrypt.hashpw(s.getPassword(), BCrypt.gensalt(10));
		s.setPassword(hashedPass);
		dataSocio.add(s);
	}
	
	public void delete(Socio s)throws Exception{
		//dataPer.remove(p);
	}
	
	public void update(Socio s)throws Exception{
		//dataPer.update(p);
	}
	
	public Socio getByUsername(Socio s) throws Exception{
		return this.dataSocio.getByUsername(s);
	}
	
	public Socio validateUser(Socio s) throws Exception{
		Socio validSoc = getByUsername(s);
		
		if(BCrypt.checkpw(s.getPassword(), validSoc.getPassword())) {
			return validSoc;
		}
		else {
			s.setId(-1);
			return s;
		}
	}
	public ArrayList<Socio> getAll()throws Exception{
		return dataSocio.getAll();
	}

}
