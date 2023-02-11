package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class GeneradorEstadoContable implements Serializable{

	private static final long serialVersionUID = 1L;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public GeneradorEstadoContable() {
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
	
	public EstadoContable crearEstadoContable(ColeccionDeTransacciones cdt) {
		return null;
	}
}
