package modelo.dao;

import java.sql.Date;
import java.util.List;

import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer> {
	public List<Movimiento> getMovimientosPorCuenta(Cuenta cuenta);
	public List<Movimiento> getMovimientosPorCuentaYFechas(Cuenta cuenta, Date fechaInicio, Date fechaFin);
}
