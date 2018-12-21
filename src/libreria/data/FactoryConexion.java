package libreria.data;

import java.sql.*;

import libreria.utils.Configuracion;


public class FactoryConexion {
	
	private static FactoryConexion instancia;
		
	private FactoryConexion(){
		try {
			
			String driver = Configuracion.getInstancia().getDbDriver();
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static FactoryConexion getInstancia(){
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia = new FactoryConexion();
		}
		return FactoryConexion.instancia;		
	}
	
	
	private Connection conn;
	private int cantConn=0;
	public Connection getConn() throws Exception{
		
		try {
			if(conn==null || conn.isClosed()){	
				
				String host = Configuracion.getInstancia().getDbHost();
				String port = Configuracion.getInstancia().getDbPort();
				String user = Configuracion.getInstancia().getDbUserName();
				String password = Configuracion.getInstancia().getDbPassword();
				String db = Configuracion.getInstancia().getDbName();
				
				conn = DriverManager.getConnection(host+":"+port+"/"+db+"?user="+user+"&password="+password);
			}
		} catch (Exception e) {
			throw new Exception("Error al conectar a la base de datos");
		}
		cantConn++;
		return conn;
	}
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	

}
