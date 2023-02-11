package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EstadoDeCuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private List<Transaccion> transacciones;
	
	public EstadoDeCuenta() {
		this.transacciones = new ArrayList<Transaccion>();
	}

	public EstadoDeCuenta(Cuenta cuenta, List<Transaccion> transacciones) {
		this.cuenta = cuenta;
		this.transacciones = transacciones;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
}
