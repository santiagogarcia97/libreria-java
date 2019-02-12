package libreria.data;
import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Sancion;

public class DataSancion {
		
	public ArrayList<Sancion> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<Sancion> sanciones= new ArrayList<Sancion>();
			try {
				stmt = FactoryConexion.getInstancia().getConn().createStatement();
				rs = stmt.executeQuery("select * from sanciones");
				
				if(rs!=null){
					while(rs.next()){
						
												
						Sancion s =new Sancion();
						s.setId(rs.getInt("id_Sancion"));
						s.setFechaSancion(rs.getDate("fecha_Sancion"));
						s.setDiasSancion(rs.getInt("dias_sancion"));
						sanciones.add(s);
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
			
			return sanciones;
			
		}
	
	public Sancion getOne(int id) throws Exception{
		Sancion s = new Sancion();
		Statement stmt=null;
		ResultSet rs=null;
		stmt = con.prepareStatement("SELECT * from sanciones WHERE  id_sancion = ?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			s.setId(rs.getInt("id_Sancion"));
			s.setFechaSancion(rs.getDate("fecha_Sancion"));
			s.setDiasSancion(rs.getInt("dias_sancion"));
		}
		return s;
	}
	
	
}
