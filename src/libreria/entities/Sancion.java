package libreria.entities;

import java.sql.Date;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import libreria.data.DataEjemplar;
import libreria.data.DataLibro;
import libreria.utils.CustomException;


public class Sancion extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private int diasSancion;
	private int idUsuario;
	private Date fechaSancion;
	public static int cant_dias = 10;
	
	public Sancion() {}
	
	public void setDiasSancion(int dias) {
		this.diasSancion = dias;
	}
	
	public void setFechaSancion(Date date) {
		this.fechaSancion = date;
	}
	
	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}
	

	public Date getFechaSancion() {
		return this.fechaSancion;
	}
	
	public int getDiasSancion() {
		return this.diasSancion;
	}
	
	public int getIdUsuario() {
		return this.idUsuario;
	}
	
	public LocalDate calc_fecha_exp() throws CustomException {		
		LocalDate fechaInicio = this.getFechaSancion().toLocalDate();
		Integer dias = this.getDiasSancion();
		return fechaInicio.plusDays(dias);
	}
	
	public int calc_dias_restantes() {
		LocalDate fecha = this.calc_fecha_exp();
		return (int) LocalDate.now().until(fecha, ChronoUnit.DAYS);
	}

}


