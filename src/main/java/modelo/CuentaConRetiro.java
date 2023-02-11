package modelo;

import java.io.Serializable;

public abstract class CuentaConRetiro extends Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private double saldo;
	
	public CuentaConRetiro() {
		super();
	}

	public CuentaConRetiro(int id, String nombre) {
		super(id, nombre);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void retirar(Transaccion transaccion) {
		//
	}

	@Override
	public void depositar(Transaccion transaccion) {
		super.depositar(transaccion);
	}
}
