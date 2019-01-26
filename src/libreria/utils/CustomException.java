package libreria.utils;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _accion = "";
	private String _origen = "";
	private Exception _excepcionOriginal = null;
	
	
	/***
	 * Constructor base para las excepciones encapsuladas
	 * @param mensaje: Mensaje personalizado sobre la excepcion.
	 * @param origen: Clase en donde se origino la excepcion.
	 * @param ex: Referencia a la excepcion original lanzada por el programa
	 */
	public CustomException(String accion, String origen, Exception ex){
		_accion = accion;
		_origen = origen;
		_excepcionOriginal = ex;
	}
	
	public String getAccion(){
		return _accion;
	}
	
	public String getOrigen(){
		return _origen;
	}
	
	public String getStack(){
		StringBuilder sb = new StringBuilder ();
		for (StackTraceElement stackElement : _excepcionOriginal.getStackTrace()) {
			sb.append(stackElement.toString());
		}
		return sb.toString();
	}
	
	@Override
	public String getMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("Mensaje: Hubo un error al ").append(getAccion()).append("\n");
		sb.append("Originada en: ").append(getOrigen()).append("\n");
		sb.append("Tipo excepcion original: ").append(_excepcionOriginal.getClass().getName()).append("\n");
		sb.append("Mensaje excepcion original: ").append(_excepcionOriginal.getMessage()).append("\n");
		sb.append("Causa excepcion original: ").append(_excepcionOriginal.getCause()).append("\n");
		sb.append("Stack Trace: ").append(getStack()).append("\n");
		
		
		return sb.toString();
	}
}
