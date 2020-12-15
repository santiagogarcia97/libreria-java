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
	
	private final String _GET_BY_ID = "select * from categorias where id=? and estado!='eliminado'"; 
	
	private final String _GET_ALL = "select * from categorias where estado!='eliminado'"; 
	
	private final String _ADD = "insert into categorias(descripcion, estado) values (?,?)"; 
	
	private final String _DELETE = 	"update categorias set estado='eliminado' where id=?"; 
	
	private final String _UPDATE = 	"update categorias set descripcion=?, estado=? where id=?"; 
	
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
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getAll()", "DataCategoria", e);		
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getAll()", "DataCategoria", e);
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
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar getById()", "DataCategoria", e);	
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar getById()", "DataCategoria", e);
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
		} catch (Exception e) {
			throw new CustomException("Error al ejecutar add()", "DataCategoria", e);	
		} finally {
			try {
				if(keyResultSet!=null)keyResultSet.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar add()", "DataCategoria", e);
			} 
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
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar delete()", "DataCategoria", e);
			}
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
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (Exception e) {
				throw new CustomException("Error al ejecutar update()", "DataCategoria", e);
			}
		}
	}
	
	public Categoria cargar_datos_a_entidad(Categoria c, ResultSet rs) {
		try {
			c.setId(rs.getInt("id"));
			c.setDesc(rs.getString("descripcion"));
			c.setEstado(rs.getString("estado"));
		}
		catch (Exception e) {
			throw new CustomException("Error al ejecutar recuperar datos.", "DataCategoria", e);		
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
