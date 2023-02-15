package modelo;

import java.io.Serializable;
import java.util.List;

public abstract class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;


	public Cuenta() {
	}
	
	public Cuenta(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public abstract double obtenerValorTotal(List<Transaccion> transacciones);
}
