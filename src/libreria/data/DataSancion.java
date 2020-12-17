package libreria.data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import libreria.entities.LineaDePrestamo;
import libreria.entities.Sancion;
import libreria.utils.CustomException;

public class DataSancion {

	private final String _GET_BY_ID = "select * from sanciones where id=? and estado!='eliminado'";

	private final String _GET_BY_SOCIO = "select * from sanciones where estado='habilitado' and id_usuario=?";

	private final String _GET_ALL =  "select * from sanciones where estado!='eliminado'"; 

	private final String _ADD = "insert into sanciones(estado, fecha_sancion, dias_sancion, id_usuario)"
			+ "values (?, ?, ?, ?)"; 

	private final String _DELETE = 	"update sanciones set estado='eliminado' where id=?"; 

	private final String _UPDATE = 	"update sanciones set estado=?, fecha_sancion=?, dias_sancion=? , id_usuario=? where id=?"; 
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Sancion> getAll() throws CustomException{

		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Sancion> Sancions= new ArrayList<Sancion>();

		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);

			if(rs!=null){
				while(rs.next()){
					Sancion s = new Sancion();
					s = cargar_datos_a_entidad(s,rs);
					Sancions.add(s);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);
			} 
		}
		return Sancions;
	}
	
	///////////////
	// GET BY SOCIO
	///////////////
	public ArrayList<Sancion> getBySocio(int id) throws CustomException{

		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Sancion> Sancions= new ArrayList<Sancion>();

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_SOCIO);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();

			if(rs!=null){
				while(rs.next()){
					Sancion s = new Sancion();
					s = cargar_datos_a_entidad(s,rs);
					Sancions.add(s);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);
			} 
		}
		return Sancions;
	}

	///////////////
	// GET BY ID
	///////////////
	public Sancion getById(Sancion san) throws CustomException{
		Sancion s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, san.getId());
			rs=stmt.executeQuery();

			if(rs!=null && rs.next()){
				s = new Sancion();
				s = cargar_datos_a_entidad(s,rs);
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getById()", "DataSancion", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getById()", "DataSancion", e);
			} 
		}
		return s;
	}


	///////////////
	// ADD
	///////////////	
	public Sancion add(Sancion s) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(s,stmt,"add");
			stmt.executeUpdate();

			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				s.setId(keyResultSet.getInt(1));
			}
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar add()", "DataSancion", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar add()", "DataSancion", e);
		} 
		return s;
	}

	///////////////
	// DELETE
	///////////////
	public void delete(Sancion s) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, s.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar delete()", "DataSancion", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar delete()", "DataSancion", e);
		}
	}

	///////////////
	// UPDATE
	///////////////
	public void update(Sancion s) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(s,stmt, "update");
			stmt.setInt(3, s.getId());

			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar update()", "DataSancion", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar update()", "DataSancion", e);
		} 
	}

	public Sancion cargar_datos_a_entidad(Sancion s, ResultSet rs) {
		try {
			s.setId(rs.getInt("id"));
			s.setFechaSancion(rs.getDate("fecha_sancion"));
			s.setDiasSancion(rs.getInt("dias_sancion"));
			s.setIdUsuario(rs.getInt("id_usuario"));
			s.setEstado(rs.getString("estado"));
		}
		catch (Exception e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataSancion", e);		
		} 
		return s;
	}

	public PreparedStatement cargar_datos_a_bd(Sancion s, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setString(1, s.getEstado());
		stmt.setDate(2, s.getFechaSancion());
		stmt.setInt(3, s.getDiasSancion());
		stmt.setInt(4, s.getIdUsuario());
		if (mode == "update") stmt.setInt(5, s.getId());

		return stmt;

	}
}
