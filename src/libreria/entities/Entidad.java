package libreria.entities;

import java.io.Serializable;

public abstract class Entidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String estado;
	
	//Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	//Getters
	public int getId() {
		return this.id;
	}
	public String getEstado() {
		return estado;
	}


	
}
