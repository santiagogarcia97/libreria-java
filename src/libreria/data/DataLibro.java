package libreria.data;
import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Libro;

public class DataLibro {
		
	public ArrayList<Libro> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<Libro> libros= new ArrayList<Libro>();
			try {
				stmt = FactoryConexion.getInstancia().getConn().createStatement();
				rs = stmt.executeQuery("select * from libros");
				
				if(rs!=null){
					while(rs.next()){
						Libro l=new Libro();
						l.setId(rs.getInt("id_libro"));
						l.setTitulo(rs.getString("titulo"));
						l.setAutor(rs.getString("autor"));
						l.setDesc(rs.getString("descripcion"));
						
						libros.add(l);
					}
				}
			} catch (SQLException e) {
				//AppDataException ade=new AppDataException(e, "Error al recuperar listado de Libros.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
				throw e;
			} catch (Exception ade){
				throw ade;
			}		
	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return libros;
			
		}
	
	public Libro getOne(int id) throws Exception{
		Libro l = new Libro();
		Statement stmt=null;
		ResultSet rs=null;
		stmt = con.prepareStatement("SELECT * from libro WHERE  id_libro = ?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			l.setTitulo(rs.getString("titulo"));
			l.setAutor(rs.getString("autor"));
			l.setDesc(rs.getString("descripcion"));
		}
		return l;
	}
	
}
