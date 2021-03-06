package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.LineaDePrestamo;
import libreria.entities.Prestamo;
import libreria.data.DataLineaDePrestamo;
import libreria.utils.CustomException;

public class DataPrestamo {

	
	private final String _GET_BY_ID = "select * from prestamos where id=? and estado!='eliminado'";

	private final String _GET_ALL =   "select * from prestamos where estado!='eliminado'"; 
							
	private final String _ADD = "insert into prestamos(id_usuario,fecha_hora_solicitud,fecha_hora_preparacion,fecha_hora_retiro,"
			+ "fecha_hora_devolucion, estado) values (?,?,?,?,?,?)";
	
	private final String _DELETE = 	"update prestamos set estado='eliminado' where id=?"; 
	
	private final String _UPDATE = 	"update prestamos set id_usuario=?, fecha_hora_solicitud=?, fecha_hora_preparacion=?, fecha_hora_retiro=?, fecha_hora_devolucion=?, estado=? where id=?"; 
	
	private final String _GET_LINEAS = "select * from lineas_prestamo where id_prestamo=? and estado!='eliminado'";
	
	private final String _COUNT_PREPARACION = "select count(*) from prestamos where fecha_hora_preparacion is null and estado!='eliminado'";
	private final String _COUNT_RETIRO =  "select count(id) from prestamos where fecha_hora_preparacion is not null "
										+ "and fecha_hora_retiro is null and estado!='eliminado'";
	private final String _COUNT_DEVOLUCION =  "select count(id) from prestamos where fecha_hora_preparacion is not null "
											+ "and fecha_hora_retiro is not null and fecha_hora_devolucion is null and estado!='eliminado'";
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Prestamo> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Prestamo> prestamos= new ArrayList<Prestamo>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Prestamo p=new Prestamo();
					p = cargar_datos_a_entidad(p,rs);
					prestamos.add(p);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataPrestamo", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataPrestamo", e);
			} 
		}
		return prestamos;
	}
	
	///////////////
	// GET BY ID
	///////////////
	public Prestamo getById(Prestamo pre) throws CustomException{
		Prestamo p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, pre.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				p = new Prestamo();
				p = cargar_datos_a_entidad(p,rs);
			}			
		} catch (Exception e) {
			throw new CustomException("Error al obtener Prestamo por id", "DataPrestamo", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener Prestamo por id", "DataPrestamo", e);
			} 
		}
		return p;
	}
	
	
	///////////////
	// ADD
	///////////////	
	public Prestamo add(Prestamo p) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(p, stmt, "add");
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));
			}
		} catch (Exception e) {
			throw new CustomException("Error al insertar Prestamo", "DataPrestamo", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al insertar Prestamo", "DataPrestamo", e);
		} 
		return p;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(Prestamo p) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Prestamo", "DataPrestamo", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Prestamo", "DataPrestamo", e);
		} 
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Prestamo p) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(p, stmt, "update");
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Prestamo", "DataPrestamo", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Prestamo", "DataPrestamo", e);
		} 
	}
	
	
	///////////////
	// COUNTERS
	///////////////
	public int countPreparacion() throws CustomException{
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int counter = 0;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_COUNT_PREPARACION);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				counter = rs.getInt(1);
			}
			 
		} catch (Exception e) {
			throw new CustomException("Error al obtener datos", "DataPrestamo", e);	
		} finally {
			// cerrar la conexion
			try {
				if(rs!=null) { rs.close(); }
				if(stmt!=null) { stmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener datos", "DataPrestamo", e);
			}
		}

		return counter;
	}
	public int countRetiro() throws CustomException{
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int counter = 0;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_COUNT_RETIRO);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				counter = rs.getInt(1);
			}
			 
		} catch (Exception e) {
			throw new CustomException("Error al obtener datos", "DataPrestamo", e);	
		} finally {
			// cerrar la conexion
			try {
				if(rs!=null) { rs.close(); }
				if(stmt!=null) { stmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener datos", "DataPrestamo", e);
			}
		}

		return counter;
	}
	public int countDevolucion() throws CustomException{
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int counter = 0;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_COUNT_DEVOLUCION);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				counter = rs.getInt(1);
			}
			 
		} catch (Exception e) {
			throw new CustomException("Error al obtener datos", "DataPrestamo", e);	
		} finally {
			// cerrar la conexion
			try {
				if(rs!=null) { rs.close(); }
				if(stmt!=null) { stmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener datos", "DataPrestamo", e);
			}
		}

		return counter;
	}
	
	
	
	
	///////////////
	// HELPERS
	///////////////
	public Prestamo cargar_datos_a_entidad(Prestamo p, ResultSet rs) {
		try {
			p.setId(rs.getInt("id"));
			p.setSocioId(Integer.parseInt(rs.getString("id_usuario")));
			p.setFechaHoraSolicitud(rs.getDate("fecha_hora_solicitud"));
			p.setFechaHoraPreparacion(rs.getDate("fecha_hora_preparacion"));
			p.setFechaHoraRetiro(rs.getDate("fecha_hora_retiro"));
			p.setFechaHoraDevolucion(rs.getDate("fecha_hora_devolucion"));
			p.setEstado(rs.getString("estado"));
			p = cargarLineas(p);
		}
		catch (Exception ex) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataPrestamo", ex);		
		} 
		return p;
	}
	
	public PreparedStatement cargar_datos_a_bd(Prestamo p, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setInt(1, p.getSocioId());
		stmt.setDate(2, p.getFechaHoraSolicitud());
		stmt.setDate(3, p.getFechaHoraPreparacion());
		stmt.setDate(4, p.getFechaHoraRetiro());
		stmt.setDate(5, p.getFechaHoraDevolucion());
		stmt.setString(6, p.getEstado());
		if (mode == "update") stmt.setInt(7, p.getId());
					
		return stmt;
	}
	
	
	public Prestamo cargarLineas(Prestamo p) {
		DataLineaDePrestamo dlp = new DataLineaDePrestamo();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(_GET_LINEAS);
			stmt.setInt(1, p.getId());
			rs = stmt.executeQuery();
			
			if(rs!=null){
				while(rs.next()){
					LineaDePrestamo lp = new LineaDePrestamo();
					lp.setId(rs.getInt("id"));
					lp= dlp.getById(lp);
					p.addLinea(lp);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar cargarLineas()", "DataPrestamo", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar cargarLineas()", "DataPrestamo", e);
			} 
		}
		return p;
	}
}
	
