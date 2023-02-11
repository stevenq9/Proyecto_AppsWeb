package modelo;

import java.io.Serializable;

public abstract class CuentaConRetiro extends Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CuentaConRetiro() {
		super();
	}

	public CuentaConRetiro(int id, String nombre, double saldo, double valorTotal) {
		super(id, nombre, saldo, valorTotal);
	}

	public void retirar(Transaccion transaccion) {
		//
	}
}
