package libreria.data;
import java.sql.*;
import java.util.ArrayList;

import libreria.entities.LineaDePrestamo;
import libreria.data.DataEjemplar;
import libreria.entities.Ejemplar;
import libreria.entities.Libro;

public class DataLineaDePrestamo {
		
	public ArrayList<LineaDePrestamo> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<LineaDePrestamo> lineas= new ArrayList<LineaDePrestamo>();
			try {
				stmt = FactoryConexion.getInstancia().getConn().createStatement();
				rs = stmt.executeQuery("select * from lineasdeprestamo");
				
				if(rs!=null){
					while(rs.next()){
						
						int id = rs.getInt("id_ejemplar");
						DataEjemplar de = DataEjemplar();
						Ejemplar e = de.getOne(id);
						
						LineaDePrestamo lp =new LineaDePrestamo();
						lp.setId(rs.getInt("id_linea"));
						lp.setDevuelto(rs.getBoolean("devuelto"));
						lp.setFechaDevolucion(rs.getDate("fecha_devolucion"));
						lp.setEjemplar(e);
					
						lineas.add(lp);
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
			
			return lineas;
			
		}
	
	public LineaDePrestamo getOne(int id) throws Exception{
		LineaDePrestamo lp = new LineaDePrestamo();
		Statement stmt=null;
		ResultSet rs=null;
		stmt = con.prepareStatement("SELECT * from lineasdeprestamo WHERE  id_linea = ?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			int ide = rs.getInt("id_ejemplar");
			DataEjemplar de = DataEjemplar();
			Ejemplar e = de.getOne(ide);
			lp.setEjemplar(e);
		}
		return lp;
	}
	
}
