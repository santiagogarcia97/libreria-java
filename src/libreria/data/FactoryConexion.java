package libreria.data;

import java.sql.*;

import libreria.utils.Configuracion;


public class FactoryConexion {
	
	private static FactoryConexion instancia;
	private Connection conn;
	private int cantConn=0;
	
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
	
	public Connection getConn() throws Exception{
		
		try {
			if(conn==null || conn.isClosed()){	
				
				String host = Configuracion.getInstancia().getDbHost();
				String port = Configuracion.getInstancia().getDbPort();
				String user = Configuracion.getInstancia().getDbUserName();
				String password = Configuracion.getInstancia().getDbPassword();
				String db = Configuracion.getInstancia().getDbName();
				
				String connString = "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password;
				
				conn = DriverManager.getConnection(connString);
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
