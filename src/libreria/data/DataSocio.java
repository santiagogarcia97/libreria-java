package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Categoria;
import libreria.entities.Libro;
import libreria.entities.Socio;
import libreria.utils.CustomException;

public class DataSocio {
	
	private final String _GET_BY_USERNAME = "select * from socios where username=? and estado!='eliminado'"; 
	
	private final String _GET_BY_ID = "select * from socios where id_socio=? and estado!='eliminado'"; 
	
	private final String _GET_ALL = "select * from socios where estado!='eliminado'"; 
	
	private final String _ADD = "insert into socios(nombre,apellido,email,domicilio,telefono,dni,username, password) "
									+ "values (?,?,?,?,?,?,?,?)"; 
	
	private final String _DELETE = 	"update socios set estado='eliminado' where id_socio=?"; 
	
	private final String _UPDATE = 	"update socios set nombre=?, apellido=?,email=?, domicilio=?,"
									+ "telefono=?, dni=?, username=?, password=?, tipo=?, estado=? where id_socio=?"; 
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Socio> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Socio> socios= new ArrayList<Socio>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Socio s=new Socio();
					s = cargar_datos_a_entidad(s,rs);
					socios.add(s);
				}
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataSocio", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataSocio", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return socios;
	}
	
	///////////////
	// GET BY ID
	///////////////
	public Socio getById(Socio soc) throws CustomException{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, soc.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					s=new Socio();
					s = cargar_datos_a_entidad(s,rs);
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al obtener Socio por id", "DataSocio", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al obtener Socio por id", "DataSocio", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return s;
	}
	
	///////////////
	// GET BY USERNAME
	///////////////	
	public Socio getByUsername(Socio soc) throws CustomException{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_USERNAME);
			stmt.setString(1, soc.getUsername());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					s=new Socio();
					s = cargar_datos_a_entidad(s,rs);
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al obtener Socio por username", "DataSocio", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al obtener Socio por username", "DataSocio", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return s;
	}
	
	///////////////
	// ADD
	///////////////	
	public Socio add(Socio s) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(s, stmt);
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				s.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new CustomException("Error al insertar Socio", "DataSocio", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al insertar Socio", "DataSocio", e);
		} catch (CustomException e) {
			throw e;					
		} 
		return s;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(Socio s) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al eliminar Socio", "DataSocio", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al eliminar Socio", "DataSocio", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Socio s) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(s, stmt);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al actualizar Socio", "DataSocio", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al actualizar Socio", "DataSocio", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
	
	public Socio cargar_datos_a_entidad(Socio s, ResultSet rs) {
		try {
			s.setId(rs.getInt("id_socio"));
			s.setUsername(rs.getString("username"));
			s.setPassword(rs.getString("password"));
			s.setNombre(rs.getString("nombre"));
			s.setApellido(rs.getString("apellido"));
			s.setEmail(rs.getString("email"));
			s.setDomicilio(rs.getString("domicilio"));
			s.setTelefono(rs.getString("telefono"));
			s.setDni(rs.getString("dni"));
			s.setTipoUsuario(rs.getString("tipo"));
			s.setEstado(rs.getString("estado"));
		}
		catch (SQLException e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataSocio", e);		
		} catch (CustomException e) {
			throw e;								
		}
		return s;
	}
	
	public PreparedStatement cargar_datos_a_bd(Socio s, PreparedStatement stmt) throws SQLException{
		stmt.setString(1, s.getNombre());
		stmt.setString(2, s.getApellido());
		stmt.setString(3, s.getEmail());
		stmt.setString(4, s.getDomicilio());
		stmt.setString(5, s.getTelefono());
		stmt.setString(6, s.getDni());
		stmt.setString(7, s.getUsername());
		stmt.setString(8, s.getPassword());
		stmt.setString(9, s.getTipoUsuario());
		stmt.setString(10, s.getEstado());
		stmt.setInt(11, s.getId());
			
		return stmt;
	}
	
}
