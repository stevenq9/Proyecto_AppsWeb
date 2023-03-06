package modelo.dao;

import java.sql.Date;
import java.util.List;

import modelo.EstadoDeCuenta;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer> {
	public List<Movimiento> getMovimientosPorCuenta(Cuenta cuenta);
	public List<Movimiento> getMovimientosPorCuentaYFechas(Cuenta cuenta, Date fechaInicio, Date fechaFin);
	public List<EstadoDeCuenta> getEstadoContableDeIngresos(Persona persona, Date fechaInicio, Date fechaFin);
	public List<EstadoDeCuenta> getEstadoContableDeIngresosYGastos(Persona persona, Date fechaInicio, Date fechaFin);
	public List<EstadoDeCuenta> getEstadoContableDeGastos(Persona persona, Date fechaInicio, Date fechaFin);
}
