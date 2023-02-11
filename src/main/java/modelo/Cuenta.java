package modelo;

import java.io.Serializable;

public abstract class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private double saldo;
	private double valorTotal;

	public Cuenta() {
	}
	
	public Cuenta(int id, String nombre, double saldo, double valorTotal) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.saldo = saldo;
		this.valorTotal = valorTotal;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void depositar(Transaccion transaccion) {
		//
	}

	public abstract void actualizarValorTotal();
}
