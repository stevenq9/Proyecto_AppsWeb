package modelo.entidades;

import java.io.Serializable;
import java.util.List;

import modelo.Transaccion;

public class CuentaDeIngresosYGastos extends Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private double saldo;

	public CuentaDeIngresosYGastos() {
		super(true, true);
	}

	public CuentaDeIngresosYGastos(int id, String nombre) {
		super(id, nombre, true, true);
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
		
		if(transaccion.getCuentaOrigen().getId() != this.getId())
			throw new Exception("La cuenta a retirar no coincide");
		
		if(saldo < transaccion.getCantidad())
			throw new Exception("Saldo insuficiente en la cuenta");
		
		this.saldo -= transaccion.getCantidad();
	}

	public void depositar(Transaccion transaccion) throws Exception {
		if(transaccion == null)
			throw new Exception("Transacción no válida");
		
		if(transaccion.getCuentaDestino().getId() != this.getId())
			throw new Exception("La cuenta a depositar no coincide");
		
		this.saldo += transaccion.getCantidad();
	}

	@Override
	public double obtenerValorTotal(List<Movimiento> movimientos) {
		return this.getSaldo();
	}
	
	public void procesarTransaccion(Transaccion transaccion) throws Exception {
		super.procesarTransaccion(transaccion);
		
		if(transaccion.getCuentaOrigen().getId() == this.getId())
			this.retirar(transaccion);
		
		if(transaccion.getCuentaDestino().getId() == this.getId())
			this.depositar(transaccion);
	}
}
