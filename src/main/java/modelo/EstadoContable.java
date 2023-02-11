package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstadoContable implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<EstadoDeCuenta> estadosDeCuenta;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public EstadoContable() {
		this.estadosDeCuenta = new ArrayList<EstadoDeCuenta>();
	}

	public EstadoContable(LocalDate fechaInicio, LocalDate fechaFin) {
		this.estadosDeCuenta = new ArrayList<EstadoDeCuenta>();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}



	public List<EstadoDeCuenta> getEstadosDeCuenta() {
		return estadosDeCuenta;
	}

	public void setEstadosDeCuenta(List<EstadoDeCuenta> estadosDeCuenta) {
		this.estadosDeCuenta = estadosDeCuenta;
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

	public void agregar(EstadoDeCuenta estadoDeCuenta) {
		this.estadosDeCuenta.add(estadoDeCuenta);
	}
}
