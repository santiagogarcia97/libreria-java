package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Socio;

public class DataSocio {
	
	public ArrayList<Socio> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Socio> socios= new ArrayList<Socio>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from socios");
			
			if(rs!=null){
				while(rs.next()){
					Socio s=new Socio();
					s.setId(rs.getInt("id_socio"));
					s.setUsername(rs.getString("username"));
					s.setPassword(rs.getString("password"));
					
					socios.add(s);
				}
			}
		} catch (SQLException e) {
			//AppDataException ade=new AppDataException(e, "Error al recuperar listado de Personas.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw e;
		} catch (Exception ade){
			throw ade;
		}		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return socios;		
	}

	public Socio getById(Socio soc) throws Exception{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_socio, username, password from socios where id_socio=?");
			stmt.setString(1, String.valueOf(soc.getId()));
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
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return s;
	}
	
	public Socio getByUsername(Socio soc) throws Exception{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from socios where username=?");
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
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return s;
	}
	
	public void add(Socio s) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into socios(nombre,apellido,email,domicilio,telefono,dni,username, password)"
							+ " values (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
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
			throw e;
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
