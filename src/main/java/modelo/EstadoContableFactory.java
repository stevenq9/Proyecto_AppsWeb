package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import modelo.entidades.Cuenta;

public class EstadoContableFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	public EstadoContableFactory() {
	}

	public EstadoContable crearEstadoContable(List<Cuenta> cuentas, List<Movimiento> movimiento) {
		
		if (fechaInicio == null || fechaFin == null)
			return null;

		if (fechaInicio.isAfter(fechaFin))
			return null;

		EstadoContable estadoContable = new EstadoContable(fechaInicio, fechaFin);

		for (Cuenta cuenta : cuentas) {
			EstadoDeCuenta estadoDeCuenta = new EstadoDeCuenta();
			estadoDeCuenta.setCuenta(cuenta);
			estadoDeCuenta.setFechaInicio(fechaInicio);
			estadoDeCuenta.setFechaFin(fechaFin);
			estadoDeCuenta.setMovimientos(chaucherita.getCatalogoDeMovimientos()
					.getMovimientosByIDCuentaYFechas(cuenta.getId(), fechaInicio, fechaFin));
			estadoDeCuenta.setValorTotal(cuenta.obtenerValorTotal(estadoDeCuenta.getMovimientos()));
			estadoContable.agregar(estadoDeCuenta);
		}

		return estadoContable;
		return null;
	}
}
