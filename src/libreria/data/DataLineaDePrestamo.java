package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Categoria;
import libreria.entities.LineaDePrestamo;
import libreria.entities.Libro;
import libreria.entities.Ejemplar;
import libreria.utils.CustomException;

public class DataLineaDePrestamo {
	
	
	private final String _GET_BY_ID = "select * from lineas_prestamo lp inner join ejemplares e on lp.id_ejemplar = e.id "
									+ "inner join libros l on e.id_libro=l.id " 
									+ "inner join categorias cl on l.id_categoria = cl.id "
									+ "where lp.id=? and lp.estado!='eliminado'";

	private final String _GET_ALL =   "select * from lineas_prestamo lp inner join ejemplares e on lp.id_ejemplar = e.id "
									+ "inner join libros l on e.id_libro=l.id " 
									+ "inner join categorias cl on l.id_categoria = cl.id "
									+ "where lp.estado!='eliminado'"; 
							
	private final String _ADD = "insert into lineas_prestamo(id_ejemplar, id_prestamo, estado, fecha_devolucion) "
									+ "values (?,?,?,?)"; 
	
	private final String _DELETE = 	"update lineas_prestamo set estado='eliminado' where id=?"; 
	
	private final String _UPDATE = 	"update lineas_prestamo set id_ejemplar=?, id_prestamo=?, estado=?, fecha_devolucion=? where id=?"; 
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<LineaDePrestamo> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<LineaDePrestamo> lineas= new ArrayList<LineaDePrestamo>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					LineaDePrestamo lp=new LineaDePrestamo();
					lp = cargar_datos_a_entidad(lp,rs);
					lineas.add(lp);
				}
			}			
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataLineaDePrestamo", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataLineaDePrestamo", e);
			} 
		}
		return lineas;
	}
	
	///////////////
	// GET BY ID
	///////////////
	public LineaDePrestamo getById(LineaDePrestamo lpr) throws CustomException{
		LineaDePrestamo lp=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, lpr.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				lp = new LineaDePrestamo();
				lp = cargar_datos_a_entidad(lp,rs);
			}			
		} catch (Exception e) {
			throw new CustomException("Error al obtener Linea por id", "DataLineaDePrestamo", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al obtener Linea por id", "DataLineaDePrestamo", e);
			} 
		}
		return lp;
	}
	
	
	///////////////
	// ADD
	///////////////	
	public LineaDePrestamo add(LineaDePrestamo lp) throws CustomException{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt = cargar_datos_a_bd(lp, stmt, "add");
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				lp.setId(keyResultSet.getInt(1));
			}
		} catch (Exception e) {
			throw new CustomException("Error al insertar Linea", "DataLineaDePrestamo", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al insertar Linea", "DataLineaDePrestamo", e);
		}  
		return lp;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(LineaDePrestamo lp) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, lp.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Linea", "DataLineaDePrestamo", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al eliminar Linea", "DataLineaDePrestamo", e);
		} 
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(LineaDePrestamo lp) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(lp, stmt, "update");
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Linea", "DataLineaDePrestamo", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception e) {
			throw new CustomException("Error al actualizar Linea", "DataLineaDePrestamo", e);
		}
	}
	
	public LineaDePrestamo cargar_datos_a_entidad(LineaDePrestamo lp, ResultSet rs) {
		Ejemplar e = new Ejemplar();
		Libro l = new Libro();
		Categoria cat = new Categoria();
		try {
			e.setId(rs.getInt("id_ejemplar"));
	//		e.setDisponible(rs.getBoolean("disponible"));
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
			cat.setId(rs.getInt("id_categoria"));
			cat.setDesc(rs.getString("descripcion"));
			l.setCat(cat);
			e.setLibro(l);
			lp.setId(rs.getInt("id"));
		//	lp.setDevuelto(rs.getBoolean("devuelto"));
			lp.setFechaDevolucion(rs.getDate("fecha_devolucion"));
			lp.setIdPrestamo(rs.getInt("id_prestamo"));
			lp.setEstado(rs.getString("estado"));
			lp.setEjemplar(e);
		}
		catch (Exception ex) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataLineaDePrestamo", ex);		
		} 
		return lp;
	}
	
	public PreparedStatement cargar_datos_a_bd(LineaDePrestamo lp, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setInt(1, lp.getEjemplar().getId());
		stmt.setInt(2, lp.getIdPrestamo());
		stmt.setString(3, lp.getEstado());
		stmt.setDate(4, lp.getFechaDevolucion());
		if (mode == "update") stmt.setInt(5, lp.getId());
					
		return stmt;
	}
	
}
