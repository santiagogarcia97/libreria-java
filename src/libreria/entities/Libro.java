package libreria.entities;

import java.sql.Date;

public class Libro extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private String titulo, autor, isbn, edicion;
	private Date fechaEdicion;
	private int diasMaxPrestamo;
	
	public Libro() {}
	
	//Setters
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}	
	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}
	public void setDiasMaxPrestamo(int diasMaxPrestamo) {
		this.diasMaxPrestamo = diasMaxPrestamo;
	}	
	
	//Getters
	public String getTitulo() {
		return this.titulo;
	}
	public String getAutor() {
		return this.autor;
	}

	public String getIsbn() {
		return isbn;
	}
	public String getEdicion() {
		return edicion;
	}
	public Date getFechaEdicion() {
		return fechaEdicion;
	}
	public int getDiasMaxPrestamo() {
		return diasMaxPrestamo;
	}

}
