package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataSocio;
import libreria.entities.Socio;
import libreria.utils.BCrypt;
import libreria.utils.CustomException;

public class CtrlSocio {
	
	private DataSocio dataSocio;	
	
	public CtrlSocio(){
		dataSocio = new DataSocio();
	}
	
	public Socio add(Socio s) throws CustomException{
		String hashedPass = BCrypt.hashpw(s.getPassword(), BCrypt.gensalt(10));
		s.setPassword(hashedPass);
		return dataSocio.add(s);
	}
	
	public Socio getByUsername(Socio s) throws CustomException{
		return this.dataSocio.getByUsername(s);
	}
	
	public Socio validateLogin(Socio s) throws CustomException{
		Socio validSoc = getByUsername(s);
		
		if(validSoc != null) {
			if(BCrypt.checkpw(s.getPassword(), validSoc.getPassword())) {
				return validSoc;
			}
			else { s.setId(-1); }
		}
		else {  s.setId(-1); }
		
		return s;
	}
	/*
	public boolean isUsernameAvailable(Socio s) throws CustomException{
		get
	}
	*/
	public ArrayList<Socio> getAll()throws CustomException{
		return dataSocio.getAll();
	}

}
