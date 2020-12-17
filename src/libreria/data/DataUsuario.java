package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.controllers.CtrlPrestamo;
import libreria.controllers.CtrlSancion;
import libreria.entities.Usuario;
import libreria.utils.CustomException;

public class DataUsuario {
	
	private final String _GET_BY_EMAIL = "select * from usuarios where email=? and estado!='eliminado'"; 
	
	private final String _GET_BY_ID = "select * from usuarios where id=? and estado!='eliminado'"; 
	
	private final String _GET_ALL = "select * from usuarios where estado!='eliminado'"; 
	
	private final String _ADD = "insert into usuarios(nombre,apellido,email,domicilio,telefono,dni,password,tipo,estado) "
									+ "values (?,?,?,?,?,?,?,?,?)"; 
	
	private final String _DELETE = 	"update usuarios set estado='eliminado' where id=?"; 
	
	private final String _UPDATE = 	"update usuarios set nombre=?, apellido=?,email=? , domicilio=?,"
									+ "telefono=?, dni=?, password=?, tipo=?, estado=? where id=?"; 
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Usuario> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Usuario u = new Usuario();
					u = cargar_datos_a_entidad(u, rs);
					usuarios.add(u);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataUsuario", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataUsuario", e);
			} 
		}
		return usuarios;
	}
	
	///////////////
	// GET BY ID
	///////////////
	public Usuario getById(Usuario user) throws CustomException{
		Usuario u = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, user.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					u=new Usuario();
					u = cargar_datos_a_entidad(u, rs);
			}			
		} catch (Exception e) {
			throw new CustomException("Error al obtener Usuario por id", "DataUsuario", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener Usuario por id", "DataUsuario", e);
			} 
		}
		return u;
	}
	
	///////////////
	// GET BY EMAIL
	///////////////	
	public Usuario getByEmail(Usuario user) throws CustomException{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_EMAIL);
			stmt.setString(1, user.getEmail());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					u = new Usuario();
					u = cargar_datos_a_entidad(u,rs);
			}			
		} catch (Exception e) {
			throw new CustomException("Error al obtener Usuario por email", "DataUsuario", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener Usuario por email", "DataUsuario", e);
			}
		}
		return u;
	}
	
	///////////////
	// ADD
	///////////////	
	public Usuario add(Usuario u) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(u, stmt, "add");
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				u.setId(keyResultSet.getInt(1));
			}
		} catch (Exception e) {
			throw new CustomException("Error al insertar Usuario", "DataUsuario", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al insertar Usuario", "DataUsuario", e);
		} 
		return u;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(Usuario u) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, u.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Usuario", "DataUsuario", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Usuario", "DataUsuario", e);
		} 
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Usuario u) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(u, stmt, "update");
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Usuario", "DataUsuario", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Usuario", "DataUsuario", e);
		} 
	}
	
	public Usuario cargar_datos_a_entidad(Usuario u, ResultSet rs) {
		try {
			u.setId(rs.getInt("id"));
			u.setPassword(rs.getString("password"));
			u.setNombre(rs.getString("nombre"));
			u.setApellido(rs.getString("apellido"));
			u.setEmail(rs.getString("email"));
			u.setDomicilio(rs.getString("domicilio"));
			u.setTelefono(rs.getString("telefono"));
			u.setDni(rs.getString("dni"));
			u.setTipoUsuario(rs.getString("tipo"));
			u.setEstado(rs.getString("estado"));
			
		}
		catch (Exception e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataUsuario", e);		
		} 
		return u;
	}

	public PreparedStatement cargar_datos_a_bd(Usuario u, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setString(1, u.getNombre());
		stmt.setString(2, u.getApellido());
		stmt.setString(3, u.getEmail());
		stmt.setString(4, u.getDomicilio());
		stmt.setString(5, u.getTelefono());
		stmt.setString(6, u.getDni());
		stmt.setString(7, u.getPassword());
		stmt.setString(8, u.getTipoUsuario());
		stmt.setString(9, u.getEstado());
		if (mode == "update") stmt.setInt(10, u.getId());
			
		return stmt;
	}
	
}
