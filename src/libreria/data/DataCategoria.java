package libreria.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import libreria.entities.Categoria;
import libreria.entities.Libro;
import libreria.utils.CustomException;

public class DataCategoria {
	
	private final String _GET_BY_ID = "select * from categorias where id_cl=? and estado!='eliminado'"; 
	
	private final String _GET_ALL = "select * from categorias where estado!='eliminado'"; 
	
	private final String _ADD = "insert into categorias(descripcion, estado) values (?,?)"; 
	
	private final String _DELETE = 	"update categorias set estado='eliminado' where id_cl=?"; 
	
	private final String _UPDATE = 	"update categorias set descripcion=?, estado=? where id_cl=?"; 
	
	///////////////
	// GET ALL
	///////////////
	public ArrayList<Categoria> getAll() throws CustomException{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Categoria> categorias= new ArrayList<Categoria>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(_GET_ALL);
			
			if(rs!=null){
				while(rs.next()){
					Categoria c = new Categoria();
					c = cargar_datos_a_entidad(c,rs);
					categorias.add(c);
				}
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getAll()", "DataCategoria", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getAll()", "DataCategoria", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return categorias;
	}
	
	///////////////
	// GET BY ID
	///////////////
	public Categoria getById(Categoria cat) throws CustomException{
		Categoria c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_GET_BY_ID);
			stmt.setInt(1, cat.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					c = new Categoria();
					c = cargar_datos_a_entidad(c,rs);
			}			
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar getById()", "DataCategoria", e);		
		} catch (CustomException e) {
			throw e;					
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new CustomException("Error al ejecutar getById()", "DataCategoria", e);
			} catch (CustomException e) {
				throw e;					
			} 
		}
		return c;
	}
	
	
	///////////////
	// ADD
	///////////////	
	public Categoria add(Categoria c) throws CustomException{
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
			throw new CustomException("Error al ejecutar add()", "DataCategoria", e);	
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar add()", "DataCategoria", e);
		} catch (CustomException e) {
			throw e;					
		} 
		return c;
	}
	
	///////////////
	// DELETE
	///////////////
	public void delete(Categoria c) throws CustomException{
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_DELETE);
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar delete()", "DataCategoria", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar delete()", "DataCategoria", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
	
	///////////////
	// UPDATE
	///////////////
	public void update(Categoria c) throws CustomException{
		PreparedStatement stmt=null;

		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(_UPDATE);
			stmt = cargar_datos_a_bd(c,stmt, "update");
			stmt.setInt(3, c.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar update()", "DataCategoria", e);	
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw new CustomException("Error al ejecutar update()", "DataCategoria", e);
		} catch (CustomException e) {
			throw e;					
		} 
	}
	
	public Categoria cargar_datos_a_entidad(Categoria c, ResultSet rs) {
		try {
			c.setId(rs.getInt("id_cl"));
			c.setDesc(rs.getString("descripcion"));
			c.setEstado(rs.getString("estado"));
		}
		catch (SQLException e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataCategoria", e);		
		} catch (CustomException e) {
			throw e;								
		}
		return c;
	}
	
	public PreparedStatement cargar_datos_a_bd(Categoria c, PreparedStatement stmt, String mode) throws SQLException{
		stmt.setString(1, c.getDesc());
		stmt.setString(2, c.getEstado());
		if (mode == "update") stmt.setInt(3, c.getId());
			
		return stmt;

	}
	
}
