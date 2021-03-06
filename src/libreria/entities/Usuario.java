package libreria.entities;

import java.util.ArrayList;

import libreria.controllers.CtrlPrestamo;
import libreria.controllers.CtrlSancion;

public class Usuario extends Entidad {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombre,apellido,email,domicilio,telefono,dni,password;
	private String tipoUsuario = "socio";
	private ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
	private ArrayList<Sancion> sanciones = new ArrayList<Sancion>();
	
	public Usuario() {}
	
	
	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	

	//Getters
	public String getNombre() {
		return this.nombre;
	}	
	public String getApellido() {
		return this.apellido;
	}	
	public String getEmail() {
		return this.email;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDni() {
		return dni;
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
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getSancionado() {
		CtrlSancion cs = new CtrlSancion();
		if(cs.isSancionado(this.getId())) {
			return "Si";
		}
		else {
			return "No";
		}
	}
	
	public String getMoroso() {
		CtrlPrestamo cp = new CtrlPrestamo();
		if(cp.isMoroso(this.getId())) {
			return "Si";
		}
		else {
			return "No";
		}
	}

}