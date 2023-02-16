package modelo;

import java.io.Serializable;
import java.util.List;

public abstract class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private final boolean PERMITE_DEPOSITO;

	public Cuenta() {
		this.PERMITE_DEPOSITO = false;
	}
	
	public Cuenta(boolean permiteDesposito) {
		this.PERMITE_DEPOSITO = permiteDesposito;
	}
	
	public Cuenta(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.PERMITE_DEPOSITO = false;
	}
	
	protected Cuenta(int id, String nombre, boolean permiteDesposito) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.PERMITE_DEPOSITO = permiteDesposito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean permiteDesposito() {
		return PERMITE_DEPOSITO;
	}

	public abstract double obtenerValorTotal(List<Transaccion> transacciones);
	
	public void procesarTransaccion(Transaccion transaccion) throws Exception {
		if(transaccion.getCuentaOrigen().getId() != this.getId() && transaccion.getCuentaDestino().getId() != this.getId())
			throw new Exception("La cuenta no participa en la transacción");
	}
}
