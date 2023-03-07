package modelo;

import java.io.Serializable;
import java.sql.Date;
import modelo.entidades.Cuenta;

public class EstadoDeCuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private double valorTotal;
	private Date fechaInicio;
	private Date fechaFin;

	public EstadoDeCuenta() {
		this.valorTotal = 0;
	}

	public EstadoDeCuenta(Cuenta cuenta, double valorTotal, Date fechaInicio, Date fechaFin) {
		super();
		this.cuenta = cuenta;
		this.valorTotal = valorTotal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
