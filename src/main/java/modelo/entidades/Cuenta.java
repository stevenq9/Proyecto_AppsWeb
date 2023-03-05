package modelo.entidades;

import java.io.Serializable;
import java.util.List;

import modelo.Transaccion;

public abstract class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private final boolean DEPOSITABLE;
	private final boolean RETIRABLE;

	public Cuenta() {
		super();
		this.DEPOSITABLE = false;
		this.RETIRABLE = false;
	}
	
	public Cuenta(boolean permiteDesposito, boolean permiteRetiro) {
		this.DEPOSITABLE = permiteDesposito;
		this.RETIRABLE = permiteRetiro;
	}
	
	public Cuenta(int id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
	}
	
	protected Cuenta(int id, String nombre, boolean permiteDesposito, boolean permiteRetiro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.DEPOSITABLE = permiteDesposito;
		this.RETIRABLE = permiteRetiro;
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

	public boolean isDepositable() {
		return DEPOSITABLE;
	}
	
	public boolean isRetirable() {
		return RETIRABLE;
	}

	public abstract double obtenerValorTotal(List<Movimiento> movimientos);
	
	public void procesarTransaccion(Transaccion transaccion) throws Exception {
		if(transaccion.getCuentaOrigen().getId() != this.getId() && transaccion.getCuentaDestino().getId() != this.getId())
			throw new Exception("La cuenta no participa en la transacción");
	}
}
