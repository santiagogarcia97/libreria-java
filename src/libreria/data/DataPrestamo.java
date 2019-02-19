package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.LineaDePrestamo;
import libreria.entities.Prestamo;
import libreria.data.DataLineaDePrestamo;
import libreria.utils.CustomException;

public class DataPrestamo {

	
	private final String _GET_BY_ID = "select * from prestamos p inner join lineas_prestamo p on p.id_linea = p.id_linea"
									+ "inner join ejemplares e on p.id_ejemplar = e.id_ejemplar"
									+ "inner join libros l on e.id_libro=l.id_libro " 
									+ "inner join categorias cl on l.id_categoria = cl.id_categoria "
									+ "where id_prestamo=? and p.estado!='eliminado'";

	private final String _GET_ALL =   "select * from prestamos p inne join lineas_prestamo p on p.id_linea = p.id_linea"
									+ "inner join ejemplares e on p.id_ejemplar = e.id_ejemplar "
									+ "inner join libros l on e.id_libro=l.id_libro " 
									+ "inner join categorias cl on l.id_categoria = cl.id_categoria "
									+ "where p.estado!='eliminado'"; 
							
	private final String _ADD = "insert into prestamos(fecha_hora_prestamo, fecha_a_devolver, dias_prestamo, estado) "
							  + "values (?,?,?,?)"; 
	
	private final String _DELETE = 	"update prestamos set estado='eliminado' where id_prestamo=?"; 
	
	private final String _UPDATE = 	"update prestamos set fecha_hora_prestamo=?, fecha_a_devolver=?, dias_prestamo=?, estado=?, where id_prestamo=?"; 
	
	private final String _GET_LINEAS = "select * from lineas_prestamo where id_prestamo = ?";
	
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
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataPrestamo", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataPrestamo", e);
			} catch (CustomException e) {
				throw e;					
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
		} catch (SQLException e) {
			throw new CustomException("Error al obtener Linea por id", "DataPrestamo", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al obtener Linea por id", "DataPrestamo", e);
			} catch (CustomException e) {
				throw e;					
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
		} catch (SQLException e) {
			throw new CustomException("Error al insertar Linea", "DataPrestamo", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al insertar Linea", "DataPrestamo", e);
		} catch (CustomException e) {
			throw e;					
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
		} catch (SQLException e) {
			throw new CustomException("Error al eliminar Linea", "DataPrestamo", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al eliminar Linea", "DataPrestamo", e);
		} catch (CustomException e) {
			throw e;					
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
		} catch (SQLException e) {
			throw new CustomException("Error al actualizar Linea", "DataPrestamo", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al actualizar Linea", "DataPrestamo", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
	
	public Prestamo cargar_datos_a_entidad(Prestamo p, ResultSet rs) {
		try {
			p.setId(rs.getInt("id_prestamo"));
			p.setFechaADevolver(rs.getDate("fecha_a_devolver"));
			p.setDiasPrestamo(rs.getInt("dias_prestamo"));
			p.setEstado(rs.getString("estado"));
			p = cargarLineas(p);
		}
		catch (SQLException ex) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataPrestamo", ex);		
		} catch (CustomException ex) {
			throw ex;								
		}
		return p;
	}
	
	public PreparedStatement cargar_datos_a_bd(Prestamo p, PreparedStatement stmt, String mode) throws SQLException{
		//fecha_hora_prestamo=?, fecha_a_devolver=?, dias_prestamo=?, estado=?, where id_prestamo=?
		stmt.setDate(1, p.getFechaHoraPrestamo());
		stmt.setDate(2, p.getFechaADevolver());
		stmt.setInt(3, p.getDiasPrestamo());
		stmt.setString(4, p.getEstado());
		if (mode == "update") stmt.setInt(5, p.getId());
					
		return stmt;
	}
	
	
	public Prestamo cargarLineas(Prestamo p) {
		DataLineaDePrestamo dlp = new DataLineaDePrestamo();
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<LineaDePrestamo> lineas= new ArrayList<LineaDePrestamo>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_LINEAS);
			
			if(rs!=null){
				while(rs.next()){
					LineaDePrestamo lp=new LineaDePrestamo();
					lp = dlp.cargar_datos_a_entidad(lp,rs);
					lineas.add(lp);
				}
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataLineaDePrestamo", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataLineaDePrestamo", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		p.setLineas(lineas);
		return p;
	}
}
	
