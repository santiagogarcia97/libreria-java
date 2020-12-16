package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Categoria;
import libreria.entities.Libro;
import libreria.utils.CustomException;

public class DataLibro {
	
	private final String _GET_BY_ID = "select * from libros l inner join categorias cl on l.id_categoria=cl.id where l.id=? and l.estado!='eliminado'";
	
	private final String _GET_BY_CAT = "select * from libros l inner join categorias cl on l.id_categoria=cl.id where cl.id=? and l.estado!='eliminado'";
	
	private final String _GET_ALL = "select * from libros l inner join categorias cl on l.id_categoria=cl.id where l.estado!='eliminado'"; 
	
	private final String _ADD = "insert into libros(isbn,titulo,autor,edicion,fecha_edicion,cant_dias_max,estado,id_categoria,imagen_tapa) "
									+ "values (?,?,?,?,?,?,?,?,?)"; 
	
	private final String _DELETE = 	"update libros set estado='eliminado' where id=?"; 
	
	private final String _UPDATE = 	"update libros set isbn=?, titulo=?,autor=?, edicion=?,"
									+ "fecha_edicion=?, cant_dias_max=?, estado=?, id_categoria=?, imagen_tapa=? where id=?"; 
		
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
					l = cargar_datos_a_entidad(l,rs);
					libros.add(l);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataLibro", e);						
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataLibro", e);
			}
		}
		return libros;
	}

	///////////////
	// GET BY CAT
	///////////////
	public ArrayList<Libro> getByCat(Categoria c) throws CustomException{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Libro> libros= new ArrayList<Libro>();
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_CAT);
			stmt.setInt(1, c.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null){
				while(rs.next()){
					Libro l=new Libro();
					l = cargar_datos_a_entidad(l,rs);
					libros.add(l);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getByCat()", "DataLibro", e);							
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getByCat()", "DataLibro", e);					
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
				l = new Libro();
				l = cargar_datos_a_entidad(l,rs);
			}			
		} catch (Exception e) {
			throw new CustomException("Error al obtener Libro por id", "DataLibro", e);						
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener Libro por id", "DataLibro", e);				
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
			stmt = cargar_datos_a_bd(l,stmt,"add");
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				l.setId(keyResultSet.getInt(1));
			}
		} catch (Exception e) {
			throw new CustomException("Error al insertar Libro", "DataLibro", e);	
		} finally {
			try {
				if(keyResultSet!=null)keyResultSet.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al insertar Libro", "DataLibro", e);				
			}
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
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Libro", "DataLibro", e);	
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al eliminar Libro", "DataLibro", e);				
			} 
		}
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Libro l) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(l,stmt, "update");
			System.out.println(stmt);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Libro", "DataLibro", e);	
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al actualizar Libro", "DataLibro", e);			
			} 
		}
	}
	
	public Libro cargar_datos_a_entidad(Libro l, ResultSet rs) {
		Categoria cat = new Categoria();
		try {
			l.setId(rs.getInt("id"));
			l.setIsbn(rs.getString("isbn"));
			l.setTitulo(rs.getString("titulo"));
			l.setAutor(rs.getString("autor"));
			l.setEdicion(rs.getString("edicion"));			
			l.setFechaEdicion(rs.getDate("fecha_edicion"));
			l.setDiasMaxPrestamo(rs.getInt("cant_dias_max"));
			l.setEstado(rs.getString("estado"));
			l.setTapa(rs.getString("imagen_tapa"));
			cat.setId(rs.getInt("id_categoria"));
			cat.setDesc(rs.getString("descripcion"));
			l.setCat(cat);
		}
		catch (Exception e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataLibro", e);										
		}
		
		return l;
	}
	
	public PreparedStatement cargar_datos_a_bd(Libro l, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setString(1, l.getIsbn());
		stmt.setString(2, l.getTitulo());
		stmt.setString(3, l.getAutor());
		stmt.setString(4, l.getEdicion());
		stmt.setDate(5, l.getFechaEdicion());
		stmt.setInt(6, l.getDiasMaxPrestamo());
		stmt.setString(7, l.getEstado());
		stmt.setInt(8, l.getCat().getId());
		stmt.setString(9, l.getTapa());
		
		if (mode == "update") stmt.setInt(10, l.getId());
	
			
		return stmt;

	}

}
