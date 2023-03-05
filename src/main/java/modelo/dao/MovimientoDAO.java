package modelo.dao;

import java.time.LocalDate;
import java.util.List;

import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer> {
	public List<Movimiento> getMovimientosPorCuenta(Cuenta cuenta);
	public List<Movimiento> getMovimientosPorCuentaYFechas(Cuenta cuenta, LocalDate fechaInicio, LocalDate fechaFin);
}
