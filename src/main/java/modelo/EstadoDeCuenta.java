package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstadoDeCuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private List<Transaccion> transacciones;
	private double valorTotal;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public EstadoDeCuenta() {
		this.transacciones = new ArrayList<Transaccion>();
		this.valorTotal = 0;
	}

	public EstadoDeCuenta(Cuenta cuenta, List<Transaccion> transacciones, LocalDate fechaInicio, LocalDate fechaFin) {
		this.transacciones = new ArrayList<Transaccion>();
		this.valorTotal = 0;
		this.cuenta = cuenta;
		this.transacciones = transacciones;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
}
