package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class GeneradorEstadoContable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public GeneradorEstadoContable() {
	}
	
	/*
	public EstadoContable crearEstadoContableDeIngresos(ColeccionDeTransacciones cdt, LocalDate fechaInicio, LocalDate fechaFin) {
		return crearEstadoContable(cdt, cdt.getChaucherita().getCuentasDeIngresos(), fechaInicio, fechaFin);
	}
	
	public EstadoContable crearEstadoContableDeIngresosYGastos(ColeccionDeTransacciones cdt, LocalDate fechaInicio, LocalDate fechaFin) {
		return crearEstadoContable(cdt, cdt.getChaucherita().getCuentasDeIngresosYGastos(), fechaInicio, fechaFin);
	}
	
	public EstadoContable crearEstadoContableDeGastos(ColeccionDeTransacciones cdt, LocalDate fechaInicio, LocalDate fechaFin) {
		return crearEstadoContable(cdt, cdt.getChaucherita().getCuentasDeGastos(), fechaInicio, fechaFin);
	}
	
	private EstadoContable crearEstadoContable(ColeccionDeTransacciones cdt, List<Cuenta> cuentas, LocalDate fechaInicio, LocalDate fechaFin) {
		if(fechaInicio == null || fechaFin == null)
			return null;
		
		if(fechaInicio.isAfter(fechaFin))
			return null;
		
		EstadoContable estadoContable = new EstadoContable(fechaInicio, fechaFin);		
		
		for(Cuenta cuenta: cuentas) {
			List<Transaccion> transaccionesDeCuentaPorFecha = ColeccionDeTransacciones.getTransacciones(fechaInicio, fechaFin, cuenta.getId());
			EstadoDeCuenta estadoDeCuenta = new EstadoDeCuenta(cuenta, transaccionesDeCuentaPorFecha, fechaInicio, fechaFin);
			estadoDeCuenta.setValorTotal(cuenta.obtenerValorTotal(transaccionesDeCuentaPorFecha));
			estadoContable.agregar(estadoDeCuenta);
		}
		
		return estadoContable;
	}
	*/
}
