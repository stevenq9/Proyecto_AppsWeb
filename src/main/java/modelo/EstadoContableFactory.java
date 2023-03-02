package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EstadoContableFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	public EstadoContableFactory() {
	}

	public EstadoContable crearEstadoContableDeIngresos(Chaucherita chaucherita, LocalDate fechaInicio,
			LocalDate fechaFin) {
		return crearEstadoContable(chaucherita, chaucherita.getCatalogoDeCuentas().getCuentasDeIngresos(), fechaInicio,
				fechaFin);
	}

	public EstadoContable crearEstadoContableDeIngresosYGastos(Chaucherita chaucherita, LocalDate fechaInicio,
			LocalDate fechaFin) {
		return crearEstadoContable(chaucherita, chaucherita.getCatalogoDeCuentas().getCuentasDeIngresosYGastos(),
				fechaInicio, fechaFin);
	}

	public EstadoContable crearEstadoContableDeGastos(Chaucherita chaucherita, LocalDate fechaInicio,
			LocalDate fechaFin) {
		return crearEstadoContable(chaucherita, chaucherita.getCatalogoDeCuentas().getCuentasDeGastos(), fechaInicio,
				fechaFin);
	}

	private EstadoContable crearEstadoContable(Chaucherita chaucherita, List<Cuenta> cuentas, LocalDate fechaInicio,
			LocalDate fechaFin) {
		
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
	}
}
