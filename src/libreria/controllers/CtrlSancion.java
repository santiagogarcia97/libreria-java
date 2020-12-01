package libreria.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

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
		this.verificarFinSanciones();
		return this.dataS.getById(s);
	}	
	
	public ArrayList<Sancion> getAll() throws CustomException{
		this.verificarFinSanciones();
		return this.dataS.getAll();
	}
	
	public ArrayList<Sancion> getBySocio(int id) throws CustomException{
		this.verificarFinSanciones();
		return this.dataS.getBySocio(id);
	}
	
	public void update(Sancion s) throws CustomException{
		this.dataS.update(s);
	}
	public void delete(Sancion s) throws CustomException{
		this.dataS.delete(s);
	}

	public Boolean isSancionado(int id) {
		ArrayList<Sancion> lista = this.getBySocio(id);
		for(Sancion s : lista) {
			if(s.getIdUsuario() == id) {
				LocalDate fechaInicial = s.getFechaSancion().toLocalDate();
				LocalDate fechaFinal = fechaInicial.plusDays(s.getDiasSancion());
				LocalDate now = new java.sql.Date(Calendar.getInstance().getTime().getTime()).toLocalDate();
				if((fechaInicial.isBefore(now)|| fechaInicial.isEqual(now)) && fechaFinal.isAfter(now)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void verificarFinSanciones() {
		ArrayList<Sancion> sanciones = this.dataS.getAll();
		for(Sancion s : sanciones) {
			if(s.calc_dias_restantes()<0) {
				this.delete(s);
			}
		}
	}

}
