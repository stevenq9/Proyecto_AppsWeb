package excepciones;

public class UsuarioYaExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioYaExisteException() {
		super("El usuario ingresado ya existe.");
	}	
}
