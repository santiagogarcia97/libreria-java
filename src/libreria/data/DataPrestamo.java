package libreria.data;
import java.sql.*;
import java.util.ArrayList;

import libreria.entities.Prestamo;
import libreria.data.DataLineaDePrestamo;
import libreria.entities.LineaDePrestamo;

public class DataPrestamo {
		
	public ArrayList<Prestamo> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<Prestamo> prestamos= new ArrayList<Prestamo>();
			try {
				stmt = FactoryConexion.getInstancia().getConn().createStatement();
				rs = stmt.executeQuery("select * from prestamos");
				
				if(rs!=null){
					while(rs.next()){
						
												
						Prestamo p =new Prestamo();
						p.setId(rs.getInt("id_prestamo"));
						p.setFechaHoraPrestamo(rs.getDate("fecha_prestamo"));
						p.setFechaADevolver(rs.getDate("fecha_devolver"));
						p.setDiasPrestamo(rs.getInt("dias_prestamo"));
						
						Statement lpstm = FactoryConexion.getInstancia().getConn().createStatement();
						ResultSet lprs = lpstm.executeQuery("select * from lineas_prestamo where id_prestamo = ?");
						lpstm.setString(1, p.getId());
						DataLineaDePrestamo dlp = DataLineaDePrestamo();
						if(lprs!=null){
							while(lprs.next()){
								LineaDePrestamo lp = LineaDePrestamo();
								lp = dlp.getOne(lprs.getInt("id_linea"));
								p.addLinea(lp);
							}
						}
						
						prestamos.add(p);
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
			
			return prestamos;
			
		}
	
	
}
