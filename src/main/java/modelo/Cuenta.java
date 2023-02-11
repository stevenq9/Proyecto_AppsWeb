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

	public void depositar(Transaccion transaccion) throws Exception{
		if(transaccion.getCantidad() < 0.01)
			throw new Exception("Se ingresó un valor menor a $0.01");
		
		if(transaccion.getCuentaOrigen() != null && !(transaccion.getCuentaOrigen() instanceof CuentaConRetiro))
			throw new Exception("Cuenta de origen no válida");
	}

	public abstract double obtenerValorTotal(List<Transaccion> transacciones);
}
