package excepciones;

public class SaldoInsuficienteException extends Exception{
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException() {
		super("El saldo en la cuenta no es suficiente.");
	}	
}
