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
					Sancion c = new Sancion();
					c = cargar_datos_a_entidad(c,rs);
					Sancions.add(c);
				}
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);
			} catch (CustomException e) {
				throw e;					
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
					Sancion c = new Sancion();
					c = cargar_datos_a_entidad(c,rs);
					Sancions.add(c);
				}
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataSancion", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return Sancions;
	}

	///////////////
	// GET BY ID
	///////////////
	public Sancion getById(Sancion cat) throws CustomException{
		Sancion c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, cat.getId());
			rs=stmt.executeQuery();

			if(rs!=null && rs.next()){
				c = new Sancion();
				c = cargar_datos_a_entidad(c,rs);
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getById()", "DataSancion", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getById()", "DataSancion", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return c;
	}


	///////////////
	// ADD
	///////////////	
	public Sancion add(Sancion c) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(c,stmt,"add");
			stmt.executeUpdate();

			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				c.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar add()", "DataSancion", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar add()", "DataSancion", e);
		} catch (CustomException e) {
			throw e;					
		} 
		return c;
	}

	///////////////
	// DELETE
	///////////////
	public void delete(Sancion c) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar delete()", "DataSancion", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar delete()", "DataSancion", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}

	///////////////
	// UPDATE
	///////////////
	public void update(Sancion c) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(c,stmt, "update");
			stmt.setInt(3, c.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar update()", "DataSancion", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar update()", "DataSancion", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}

	public Sancion cargar_datos_a_entidad(Sancion c, ResultSet rs) {
		try {
			c.setId(rs.getInt("id"));
			c.setFechaSancion(rs.getDate("fecha_sancion"));
			c.setDiasSancion(rs.getInt("dias_sancion"));
			c.setIdUsuario(rs.getInt("id_usuario"));
			c.setEstado(rs.getString("estado"));
		}
		catch (SQLException e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataSancion", e);		
		} catch (CustomException e) {
			throw e;								
		}
		return c;
	}

	public PreparedStatement cargar_datos_a_bd(Sancion c, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setString(1, c.getEstado());
		stmt.setDate(2, c.getFechaSancion());
		stmt.setInt(3, c.getDiasSancion());
		stmt.setInt(4, c.getIdUsuario());
		if (mode == "update") stmt.setInt(5, c.getId());

		return stmt;

	}
	
	
	/////////////////
	// IS SANCIONADO
	/////////////////
	public Boolean IsSancionado(int id) {
		ArrayList<Sancion> lista = this.getBySocio(id);
		for(Sancion s : lista) {
			if(s.getIdUsuario() == id) {
				LocalDate fechaInicial = s.getFechaSancion().toLocalDate();
				LocalDate fechaFinal = fechaInicial.plusDays(s.getDiasSancion());
				LocalDate now = new java.sql.Date(Calendar.getInstance().getTime().getTime()).toLocalDate();
				if(fechaInicial.isBefore(now) && fechaFinal.isAfter(now)) {
					return true;
				}
			}
		}
		return false;
	}
}
