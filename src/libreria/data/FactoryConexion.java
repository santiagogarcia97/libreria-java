package libreria.data;

import java.sql.*;

import libreria.utils.Configuracion;
import libreria.utils.CustomException;


public class FactoryConexion {
	
	private static FactoryConexion instancia;
	private Connection conn;
	private int cantConn=0;
	
	private FactoryConexion() throws CustomException{
		try {
			
			String driver = Configuracion.getInstancia().getDbDriver();
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			throw new CustomException("No se pudo cargar el driver de conexion", "FactoryConexion", e);
		} catch (CustomException e) {
			throw e;
		} catch(Exception e) {
			throw new CustomException("Error al instanciar", "FactoryConexion", e);
		}
	}
	
	public static FactoryConexion getInstancia() throws CustomException{
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia = new FactoryConexion();
		}
		return FactoryConexion.instancia;		
	}
	
	public Connection getConn() throws CustomException{
		
		try {
			if(conn==null || conn.isClosed()){	
				
				String host = Configuracion.getInstancia().getDbHost();
				String port = Configuracion.getInstancia().getDbPort();
				String user = Configuracion.getInstancia().getDbUserName();
				String password = Configuracion.getInstancia().getDbPassword();
				String db = Configuracion.getInstancia().getDbName();
				
				String connString = "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password+"&autoReconnect=true";
				
				conn = DriverManager.getConnection(connString);
			}
		} catch (SQLException e) {
			throw new CustomException("No se pudo conectar a la BD", "FactoryConexion", e);
		}
		cantConn++;
		return conn;
	}
	
	public void releaseConn() throws CustomException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw new CustomException("Error al intentar cerrar la conexion", "FactoryConexion", e); 
		}
	}
	

}
