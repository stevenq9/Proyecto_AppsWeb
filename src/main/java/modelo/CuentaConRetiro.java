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

	public void retirar(Transaccion transaccion) throws Exception {
		if(transaccion.getCantidad() < 0.01)
			throw new Exception("La cantidad ingresada no es válida");
		
		if(saldo < transaccion.getCantidad())
			throw new Exception("Saldo insuficiente en la cuenta");
		
		if(transaccion.getCuentaOrigen() != null && !(transaccion.getCuentaOrigen() instanceof CuentaConRetiro))
			throw new Exception("Cuenta de origen no válida");
		
		this.saldo -= transaccion.getCantidad();
	}

	@Override
	public void depositar(Transaccion transaccion) throws Exception {
		super.depositar(transaccion);
		this.saldo += transaccion.getCantidad();
	}
}
