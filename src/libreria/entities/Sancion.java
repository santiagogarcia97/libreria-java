package libreria.entities;

import java.sql.Date;
import java.time.*;


public class Sancion extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private int diasSancion;
	private Date fechaSancion;
	
	
	public Sancion() {}
	
	public void setDiasSancion(int dias) {
		this.diasSancion = dias;
	}
	
	public void setFechaSancion(Date date) {
		this.fechaSancion = date;
	}
	

	public Date getFechaSancion() {
		return this.fechaSancion;
	}
	
	public int getDiasSancion() {
		return this.diasSancion;
	}
}


