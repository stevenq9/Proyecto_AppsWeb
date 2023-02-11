package modelo;

import java.io.Serializable;
import java.util.Date;

public class GeneradorEstadoContable implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date fechaInicio;
	private Date fechaFin;
	
	public GeneradorEstadoContable() {
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
	
	public EstadoContable crearEstadoContable(ColeccionDeTransacciones cdt) {
		return null;
	}
}
