package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstadoDeCuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private List<Movimiento> movimientos;
	private double valorTotal;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public EstadoDeCuenta() {
		this.movimientos = new ArrayList<Movimiento>();
		this.valorTotal = 0;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	public void clasificarMovimientos(List<Movimiento> listaMovimientos) {
	
		if(fechaInicio == null || fechaFin == null)
			return;
		
		if(fechaInicio.isAfter(fechaFin))
			return;
	 
		for (Movimiento m : listaMovimientos) {
			if ((m.getFecha().isAfter(fechaInicio) || m.getFecha().isEqual(fechaInicio))
					&& (m.getFecha().isBefore(fechaFin) || m.getFecha().isEqual(fechaFin))
					&& (m.getCuentaOrigen().getId() == this.cuenta.getId()
							|| m.getCuentaDestino().getId() == this.cuenta.getId())) {
				this.movimientos.add(m);
			}
		}

	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
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

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
}
