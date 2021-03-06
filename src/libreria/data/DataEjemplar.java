package libreria.data;
import java.sql.*;
import java.util.ArrayList;

import libreria.data.DataEjemplar;
import libreria.entities.Categoria;
import libreria.entities.Ejemplar;
import libreria.entities.Libro;
import libreria.utils.CustomException;

public class DataEjemplar {
	
	private final String _GET_BY_ID = "select * from ejemplares e inner join libros l on e.id_libro=l.id " 
									+ "inner join categorias cl on l.id_categoria = cl.id "
									+ "where e.id=? and e.estado!='eliminado'";
	
	private final String _GET_ONE_BY_LIBRO = "select * from ejemplares where estado='habilitado' and id_libro=? limit 1";
		
	private final String _GET_ALL =   "select * from ejemplares e inner join libros l on e.id_libro=l.id " 
									+ "inner join categorias cl on l.id_categoria = cl.id "
									+ "where e.estado='habilitado'"; 
	
	private final String _ADD = "insert into ejemplares(estado, id_libro)"
									+ "values (?, ?)"; 
	
	private final String _DELETE = 	"update ejemplares set estado='eliminado' where id=?"; 
	
	private final String _UPDATE = 	"update ejemplares set estado=? , id_libro=? where id=?"; 
	
	private final String _GET_COUNT_BY_LIBRO = "select count(*) as total from ejemplares where estado='habilitado' and id_libro=?";
	
	
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Ejemplar> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Ejemplar> ejemplares= new ArrayList<Ejemplar>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Ejemplar e=new Ejemplar();
					e = cargar_datos_a_entidad(e,rs);
					ejemplares.add(e);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataEjemplar", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataEjemplar", e);
			}
		}
		return ejemplares;
	}
	
	
	///////////////
	// GET BY ID
	///////////////
	public Ejemplar getById(Ejemplar Ejemplar) throws CustomException{
		Ejemplar e=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, Ejemplar.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				e = new Ejemplar();
				e.setId(rs.getInt("id"));
				Libro l = new Libro();
				l.setId(rs.getInt("id_libro"));
				e.setLibro(l);
				e.setEstado(rs.getString("estado"));
			}			
		} catch (Exception ex) {
			throw new CustomException("Error al obtener Ejemplar por id", "DataEjemplar", ex);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception ex) {
				throw new CustomException("Error al obtener Ejemplar por id", "DataEjemplar", ex);
			} 
		}
		return e;
	}
	
	//////////////////////
	// GET COUNT BY LIBRO
	//////////////////////
	public Integer getCountByLibro(Libro l) throws CustomException{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Integer total = -1;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_COUNT_BY_LIBRO);
			stmt.setInt(1, l.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null){
				while(rs.next()){
					total = rs.getInt("total");
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getCountByLibro()", "DataEjemplar", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getCountByLibro()", "DataEjemplar", e);
			} 
		}
		return total;
	}
	
	////////////////////
	// GET ONE BY LIBRO
	////////////////////
	public Ejemplar getOneByLibro(Libro l) throws CustomException{
		Ejemplar e=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_ONE_BY_LIBRO);
			stmt.setInt(1, l.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				e = new Ejemplar();
				e.setId(rs.getInt("id"));
				e.setLibro(l);
				e.setEstado(rs.getString("estado"));
			}			
		} catch (Exception ex) {
			throw new CustomException("Error al obtener Ejemplar por Libro", "DataEjemplar", ex);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception ex) {
				throw new CustomException("Error al obtener Ejemplar por Libro", "DataEjemplar", ex);
			} 
		}
		return e;
	}	
	
	///////////////
	// ADD
	///////////////	
	public Ejemplar add(Ejemplar e) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(e,stmt,"add");
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				e.setId(keyResultSet.getInt(1));
			}
		} catch (Exception ex) {
			throw new CustomException("Error al insertar Ejemplar", "DataEjemplar", ex);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception ex) {
			throw new CustomException("Error al insertar Ejemplar", "DataEjemplar", ex);
		} 
		return e;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(Ejemplar e) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, e.getId());
			stmt.executeUpdate();
		} catch (Exception ex) {
			throw new CustomException("Error al eliminar Ejemplar", "DataEjemplar", ex);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception ex) {
			throw new CustomException("Error al eliminar Ejemplar", "DataEjemplar", ex);
		}
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Ejemplar e) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(e,stmt,"update");
			stmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new CustomException("Error al actualizar Ejemplar", "DataEjemplar", ex);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception ex) {
			throw new CustomException("Error al actualizar Ejemplar", "DataEjemplar", ex);
		} 
	}
		
	
	public Ejemplar cargar_datos_a_entidad(Ejemplar e, ResultSet rs) {
		Libro l = new Libro();
		Categoria cat = new Categoria();
		try {
			e.setId(rs.getInt("id"));

			e.setEstado(rs.getString("estado"));
			l.setId(rs.getInt("id_libro"));
			l.setIsbn(rs.getString("isbn"));
			l.setTitulo(rs.getString("titulo"));
			l.setAutor(rs.getString("autor"));
			l.setEdicion(rs.getString("edicion"));			
			l.setFechaEdicion(rs.getDate("fecha_edicion"));
			l.setDiasMaxPrestamo(rs.getInt("cant_dias_max"));
			l.setEstado(rs.getString("estado"));
			l.setTapa(rs.getString("imagen_tapa"));
			cat.setId(rs.getInt("id"));
			cat.setDesc(rs.getString("descripcion"));
			l.setCat(cat);
		}
		catch (Exception ex) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataEjemplar", ex);		
		}
		e.setLibro(l);
		return e;
	}
	
	public PreparedStatement cargar_datos_a_bd(Ejemplar e, PreparedStatement stmt, String mode) throws SQLException{
	
		stmt.setString(1, e.getEstado());

		stmt.setInt(2, e.getLibro().getId());
		if (mode == "update") stmt.setInt(3, e.getId());
		
			
		return stmt;
	
	}
		
}
