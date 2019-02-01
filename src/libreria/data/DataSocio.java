package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Socio;
import libreria.utils.CustomException;

public class DataSocio {
	
	private final String GET_BY_USERNAME = "select * from socios where username=? and estado!='eliminado'"; 
	private final String GET_BY_ID = "select * from socios where id_socio=? and estado!='eliminado'"; 
	private final String GET_ALL = "select * from socios where estado!='eliminado'"; 
	private final String ADD = 	"insert into socios(nombre,apellido,email,domicilio,telefono,dni,username, password) values (?,?,?,?,?,?,?,?)"; 
	
	public ArrayList<Socio> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Socio> socios= new ArrayList<Socio>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Socio s=new Socio();

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

	public Socio getById(Socio soc) throws CustomException{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(GET_BY_ID);
			stmt.setInt(1, soc.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					s=new Socio();
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
	
	public Socio getByUsername(Socio soc) throws CustomException{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(GET_BY_USERNAME);
			stmt.setString(1, soc.getUsername());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					s=new Socio();
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
	
	public void add(Socio s) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getNombre());
			stmt.setString(2, s.getApellido());
			stmt.setString(3, s.getEmail());
			stmt.setString(4, s.getDomicilio());
			stmt.setString(5, s.getTelefono());
			stmt.setString(6, s.getDni());
			stmt.setString(7, s.getUsername());
			stmt.setString(8, s.getPassword());

			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				s.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new CustomException("Error al insertar socio", "DataSocio", e);	
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
	}
}
