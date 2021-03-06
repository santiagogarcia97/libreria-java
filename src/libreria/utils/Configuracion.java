package libreria.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

	private static Configuracion instancia = null;
	
	private static String dbHost = "";
	private static String dbPort = "";
	private static String dbName = "";
	private static String dbUser = "";
	private static String dbPass = "";
	private static String dbDriver = "";

	private Configuracion() throws CustomException{
		
		Properties prop = new Properties();
		
        try {
        	prop.load(getClass().getResourceAsStream("config.cfg"));
			
			dbHost = prop.getProperty("HOST").toString();
			dbPort = prop.getProperty("PORT").toString();
			dbName = prop.getProperty("DB").toString();
	        dbUser = prop.getProperty("USER").toString();
	        dbPass = prop.getProperty("PASS").toString();
	        dbDriver = prop.getProperty("DRIVER").toString();

	        
		} catch (FileNotFoundException e) {
			throw new CustomException("El archivo de configuracion no fue encontrado", "Utils.Configuracion", e);
		} catch (IOException e) {
			throw new CustomException("Error al cargar el archivo de configuracion o al leer una propiedad", "Utils.Configuracion", e);
		}
	}
	
	public static Configuracion getInstancia() throws CustomException{
		if (instancia == null) {
			instancia = new Configuracion();
		}
		return instancia;
	}
	
	public String getDbHost(){
		return dbHost;
	}
	
	public String getDbPort(){
		return dbPort;
	}
	
	public String getDbName(){
		return dbName;
	}
	
	public String getDbUserName(){
		return dbUser;
	}
	
	public String getDbPassword(){
		return dbPass;
	}
	
	public String getDbDriver(){
		return dbDriver;
	}
	
}
