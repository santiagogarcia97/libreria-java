package libreria.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String accion = "";
	private String origen = "";
	private Exception excepcionOriginal = null;
	
	
	public CustomException(String accion, String origen, Exception ex){
		this.accion = accion;
		this.origen = origen;
		this.excepcionOriginal = ex;
	}
	
	public String getAccion(){
		return accion;
	}
	
	public String getOrigen(){
		return origen;
	}
	
	public String getStack(){
		StringBuilder sb = new StringBuilder ();
		for (StackTraceElement stackElement : excepcionOriginal.getStackTrace()) {
			sb.append(stackElement.toString());
		}
		return sb.toString();
	}
	
	@Override
	public String getMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("HORA: ").append(new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(Calendar.getInstance().getTime())).append("\n");
		sb.append("MENSAJE: ").append(getAccion()).append("\n");
		sb.append("ORIGINADA EN: ").append(getOrigen()).append("\n\n");
		sb.append("TIPO EXCEPCION ORIGINAL: ").append(excepcionOriginal.getClass().getName()).append("\n");
		sb.append("MENSAJE EXCEPCION ORIGINAL: ").append(excepcionOriginal.getMessage()).append("\n");	
		
		return sb.toString();
	}
}
