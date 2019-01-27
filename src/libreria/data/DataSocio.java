package libreria.data;

import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Socio;

public class DataSocio {
	public ArrayList<Socio> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Socio> socios= new ArrayList<Socio>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from socios");
			
			if(rs!=null){
				while(rs.next()){
					Socio s=new Socio();
					s.setId(rs.getInt("id_socio"));
					s.setUsername(rs.getString("username"));
					s.setPassword(rs.getString("password"));
					
					socios.add(s);
				}
			}
		} catch (SQLException e) {
			//AppDataException ade=new AppDataException(e, "Error al recuperar listado de Personas.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
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
		
		return socios;
		
	}
	
}
