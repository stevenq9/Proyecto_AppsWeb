package modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import excepciones.SaldoInsuficienteException;

@Entity
public class CuentaDeIngresosYGastos extends Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "saldo")
	private double saldo;

	public CuentaDeIngresosYGastos() {
		super();
	}

	public CuentaDeIngresosYGastos(String nombre, Persona propietario) {
		super(nombre, propietario);
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

	public void retirar(Movimiento movimiento) throws Exception {		
		if(movimiento == null)
			throw new Exception("Transacción no válida");
		
		if(movimiento.getCuentaOrigen().getId() != this.getId())
			throw new Exception("La cuenta a retirar no coincide");
		
		if(saldo < movimiento.getCantidad())
			throw new SaldoInsuficienteException();
		
		this.saldo -= movimiento.getCantidad();
	}

	public void depositar(Movimiento movimiento) throws Exception {
		if(movimiento == null)
			throw new Exception("Transacción no válida");
		
		if(movimiento.getCuentaDestino().getId() != this.getId())
			throw new Exception("La cuenta a depositar no coincide");
		
		this.saldo += movimiento.getCantidad();
	}
	
	/*public void procesarTransaccion(Transaccion transaccion) throws Exception {
		super.procesarTransaccion(transaccion);
		
		if(transaccion.getCuentaOrigen().getId() == this.getId())
			this.retirar(transaccion);
		
		if(transaccion.getCuentaDestino().getId() == this.getId())
			this.depositar(transaccion);
	}*/
}
