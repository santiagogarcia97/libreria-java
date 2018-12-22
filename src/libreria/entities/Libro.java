package libreria.entities;

public class Libro extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	private String titulo, autor, desc;
	
	public Libro() {}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	public String getAutor() {
		return this.autor;
	}
	public String getDesc() {
		return this.desc;
	}
}
