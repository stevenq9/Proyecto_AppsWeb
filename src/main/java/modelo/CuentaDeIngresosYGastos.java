package modelo;

import java.io.Serializable;
import java.util.List;

public class CuentaDeIngresosYGastos extends Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private double saldo;

	public CuentaDeIngresosYGastos() {
		super();
	}

	public CuentaDeIngresosYGastos(int id, String nombre) {
		super(id, nombre);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void retirar(Transaccion transaccion) throws Exception {		
		if(transaccion == null)
			throw new Exception("Transacción no válida");
		
		if(saldo < transaccion.getCantidad())
			throw new Exception("Saldo insuficiente en la cuenta");
		
		this.saldo -= transaccion.getCantidad();
	}

	public void depositar(Transaccion transaccion) throws Exception {
		if(transaccion == null)
			throw new Exception("Transacción no válida");
		this.saldo += transaccion.getCantidad();
	}

	@Override
	public double obtenerValorTotal(List<Transaccion> transacciones) {
		return this.getSaldo();
	}
}
