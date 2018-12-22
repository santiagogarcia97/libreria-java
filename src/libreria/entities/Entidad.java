package libreria.entities;

import java.io.Serializable;

public abstract class Entidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
}
