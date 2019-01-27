package libreria.entities;

import java.util.ArrayList;

public class Socio extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombre; 
	private String apellido;
	private String email;
	private String estado;
	private String username;
	private String password;
	private ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
	private ArrayList<Sancion> sanciones = new ArrayList<Sancion>();
	
	public Socio() {}
	
	//TODO: agregar array de sanciones
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addPrestamo(Prestamo prestamo) {
		this.prestamos.add(prestamo);
	}
	
	public void addSancion(Sancion sancion) {
		this.sanciones.add(sancion);
	}
	

	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public ArrayList<Prestamo> getPrestamos() {
		return this.prestamos;
	}
	
	public ArrayList<Sancion> getSanciones() {
		return this.sanciones;
	}
}