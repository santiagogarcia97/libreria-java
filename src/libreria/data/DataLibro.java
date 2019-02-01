package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Libro;
import libreria.utils.CustomException;

public class DataLibro {
	
	private final String _GET_BY_ID = "select * from libros where id_libro=? and estado!='eliminado'"; 
	
	private final String _GET_ALL = "select * from libros where estado!='eliminado'"; 
	
	private final String _ADD = "insert into libros(isbn,titulo,autor,edicion,fecha_edicion,cant_dias_max,estado) "
									+ "values (?,?,?,?,?,?,?)"; 
	
	private final String _DELETE = 	"update libros set estado='eliminado' where id_libro=?"; 
	
	private final String _UPDATE = 	"update libros set isbn=?, titulo=?,autor=?, edicion=?,"
									+ "fecha_edicion=?, cant_dias_max=?, estado=? where id_libro=?"; 
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Libro> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Libro> libros= new ArrayList<Libro>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Libro l=new Libro();

					l.setId(rs.getInt("id_libro"));
					l.setIsbn(rs.getString("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setAutor(rs.getString("autor"));
					l.setEdicion(rs.getString("edicion"));			
					l.setFechaEdicion(rs.getDate("fecha_edicion"));
					l.setDiasMaxPrestamo(rs.getInt("cant_dias_max"));
					l.setEstado(rs.getString("estado"));
					
					libros.add(l);
				}
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataLibro", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataLibro", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return libros;
	}
	
	///////////////
	// GET BY ID
	///////////////
	public Libro getById(Libro libro) throws CustomException{
		Libro l=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, libro.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					l=new Libro();
					
					l.setId(rs.getInt("id_libro"));
					l.setIsbn(rs.getString("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setAutor(rs.getString("autor"));
					l.setEdicion(rs.getString("edicion"));			
					l.setFechaEdicion(rs.getDate("fecha_edicion"));
					l.setDiasMaxPrestamo(rs.getInt("cant_dias_max"));
					l.setEstado(rs.getString("estado"));
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al obtener Libro por id", "DataLibro", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al obtener Libro por id", "DataLibro", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return l;
	}
	
	///////////////
	// ADD
	///////////////	
	public Libro add(Libro l) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, l.getIsbn());
			stmt.setString(2, l.getTitulo());
			stmt.setString(3, l.getAutor());
			stmt.setString(4, l.getEdicion());
			stmt.setDate(5, l.getFechaEdicion());
			stmt.setInt(6, l.getDiasMaxPrestamo());
			stmt.setString(7, l.getEstado());

			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				l.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new CustomException("Error al insertar Libro", "DataLibro", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al insertar Libro", "DataLibro", e);
		} catch (CustomException e) {
			throw e;					
		}
		return l;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(Libro l) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, l.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al eliminar Libro", "DataLibro", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al eliminar Libro", "DataLibro", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Libro l) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt.setString(1, l.getIsbn());
			stmt.setString(2, l.getTitulo());
			stmt.setString(3, l.getAutor());
			stmt.setString(4, l.getEdicion());
			stmt.setDate(5, l.getFechaEdicion());
			stmt.setInt(6, l.getDiasMaxPrestamo());
			stmt.setString(7, l.getEstado());
			stmt.setInt(8, l.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al actualizar Libro", "DataLibro", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al actualizar Libro", "DataLibro", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
}
