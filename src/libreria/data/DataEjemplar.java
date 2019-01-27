package libreria.data;
import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Ejemplar;
import libreria.data.DataLibro;
import libreria.entities.Libro;

public class DataEjemplar {
		
	public ArrayList<Ejemplar> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<Ejemplar> ejemplares= new ArrayList<Ejemplar>();
			try {
				stmt = FactoryConexion.getInstancia().getConn().createStatement();
				rs = stmt.executeQuery("select * from ejemplar");
				
				if(rs!=null){
					while(rs.next()){
						Ejemplar e =new Ejemplar();
						
						int id = rs.getInt("id_libro");
						DataLibro dl = DataLibro();
						Libro l = dl.getOne(id);

						e.setLibro(l);
					
						ejemplares.add(e);
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
	
}
