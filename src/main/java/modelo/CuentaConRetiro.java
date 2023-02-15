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
		if(saldo < transaccion.getCantidad())
			throw new Exception("Saldo insuficiente en la cuenta");
		
		if(transaccion.getCuentaOrigen().getId() != this.getId())
			throw new Exception("Cuenta de origen de la transacción no corresponde con la cuenta escogida.");
		
		this.saldo -= transaccion.getCantidad();
	}

	@Override
	public void depositar(Transaccion transaccion) throws Exception {
		super.depositar(transaccion);
		this.saldo += transaccion.getCantidad();
	}
}
