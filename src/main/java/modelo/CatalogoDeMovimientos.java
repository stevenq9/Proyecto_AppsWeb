package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.CuentaDeGastos;
import modelo.entidades.CuentaDeIngresos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Movimiento;
import modelo.entidades.Tipo;

public class CatalogoDeMovimientos implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Movimiento> movimientos;

	public CatalogoDeMovimientos() {
		this.movimientos = new ArrayList<>();
	}

	public void agregarIngreso(LocalDate fecha, CuentaDeIngresos cuentaOrigen, CuentaDeIngresosYGastos cuentaDestino,
			String descripcion, double cantidad) {
		Movimiento ingreso = new Movimiento(fecha, cuentaOrigen, cuentaDestino, descripcion, cantidad, Tipo.INGRESO);
		this.agregarMovimiento(ingreso);
	}

	public void agregarGasto(LocalDate fecha, CuentaDeIngresosYGastos cuentaOrigen, CuentaDeGastos cuentaDestino,
			String descripcion, double cantidad) {
		Movimiento gasto = new Movimiento(fecha, cuentaOrigen, cuentaDestino, descripcion, cantidad, Tipo.GASTO);
		this.agregarMovimiento(gasto);
	}

	public void agregarTransferencia(LocalDate fecha, CuentaDeIngresosYGastos cuentaOrigen,
			CuentaDeIngresosYGastos cuentaDestino, String descripcion, double cantidad) {
		Movimiento transferencia = new Movimiento(fecha, cuentaOrigen, cuentaDestino, descripcion, cantidad,
				Tipo.TRANSFERENCIA);
		this.agregarMovimiento(transferencia);
	}

	private void agregarMovimiento(Movimiento movimiento) {
		
		int max = 0;

		for (Movimiento m : getMovimientos()) {
			if (max < m.getId()) {
				max = m.getId();
			}
		}
		movimiento.setId(max + 1);
		movimientos.add(movimiento);
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public List<Movimiento> getMovimientosByIDCuenta(int idCuenta) {
		List<Movimiento> temp = new ArrayList<>();
		for (Movimiento movimiento : this.movimientos) {
			if (idCuenta == movimiento.getCuentaOrigen().getId() || idCuenta == movimiento.getCuentaDestino().getId()) {
				temp.add(movimiento);
			}
		}
		return temp;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public List<Movimiento> getMovimientosByIDCuentaYFechas(int id, LocalDate fechaInicio, LocalDate fechaFin) {

		if (fechaInicio == null || fechaFin == null)
			return null;

		if (fechaInicio.isAfter(fechaFin))
			return null;

		List<Movimiento> listaMovimientos = new ArrayList<>();
		for (Movimiento m : this.movimientos) {
			if ((m.getFecha().isAfter(fechaInicio) || m.getFecha().isEqual(fechaInicio))
					&& (m.getFecha().isBefore(fechaFin) || m.getFecha().isEqual(fechaFin))
					&& (m.getCuentaOrigen().getId() == id || m.getCuentaDestino().getId() == id)) {
				listaMovimientos.add(m);
			}
		}
		return listaMovimientos;
	}
}
