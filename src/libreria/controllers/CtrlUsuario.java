package libreria.controllers;

import java.util.ArrayList;

import libreria.data.DataUsuario;
import libreria.entities.Libro;
import libreria.entities.Usuario;
import libreria.utils.BCrypt;
import libreria.utils.CustomException;

public class CtrlUsuario {
	
	private DataUsuario dataUsuario;	
	
	public CtrlUsuario(){
		dataUsuario = new DataUsuario();
	}
	
	public Usuario add(Usuario u) throws CustomException{
		String hashedPass = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(10));
		u.setPassword(hashedPass);
		u.setEstado("habilitado");
		return dataUsuario.add(u);
	}
	
	public Usuario getByEmail(Usuario u) throws CustomException{
		return this.dataUsuario.getByEmail(u);
	}
	
	public void update(Usuario u) throws CustomException{
		this.dataUsuario.update(u);
	}
	
	public void delete(Usuario u) throws CustomException{
		this.dataUsuario.delete(u);
	}
	
	public Usuario validateLogin(Usuario u) throws CustomException{
		Usuario validSoc = getByEmail(u);
		
		if(validSoc != null) {
			if(BCrypt.checkpw(u.getPassword(), validSoc.getPassword())) {
				return validSoc;
			}
			else { u.setId(-1); }
		}
		else {  u.setId(-1); }
		
		return u;
	}
	/*
	public boolean isUsernameAvailable(Socio s) throws CustomException{
		get
	}
	*/
	public ArrayList<Usuario> getAll()throws CustomException{
		return dataUsuario.getAll();
	}
	
	public Usuario getById(Usuario u) throws CustomException{
		return this.dataUsuario.getById(u);
	}

}
