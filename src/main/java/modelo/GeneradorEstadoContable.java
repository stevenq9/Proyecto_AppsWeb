package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class GeneradorEstadoContable implements Serializable{

	private static final long serialVersionUID = 1L;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public GeneradorEstadoContable() {
	}

	public GeneradorEstadoContable(LocalDate fechaInicio, LocalDate fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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
		if(fechaInicio == null || fechaFin == null)
			return null;
		
		if(fechaInicio.isAfter(fechaFin))
			return null;
		
		EstadoContable estadoContable = new EstadoContable();
		List<Cuenta> cuentas = cdt.getChaucherita().getCuentas();
		
		
		for(Cuenta cuenta: cuentas) {
			EstadoDeCuenta estadoDeCuenta = new EstadoDeCuenta(cuenta, ColeccionDeTransacciones.getTransacciones(fechaInicio, fechaFin, cuenta.getId()));
			estadoContable.agregar(estadoDeCuenta);
		}
		
		return estadoContable;
	}
}
