package modelo.dao;

import java.sql.Date;
import java.util.List;

import modelo.EstadoDeCuenta;
import modelo.entidades.Cuenta;
import modelo.entidades.Persona;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer> {
	public List<Cuenta> getCuentasIngresosPorPersona(Persona persona);
	public List<Cuenta> getCuentasIngresosYGastosPorPersona(Persona persona);
	public List<Cuenta> getCuentasGastosPorPersona(Persona persona);
	public List<EstadoDeCuenta> getEstadoContableDeIngresos(Persona persona, Date fechaInicio, Date fechaFin);
	public List<EstadoDeCuenta> getEstadoContableDeIngresosYGastos(Persona persona, Date fechaInicio, Date fechaFin);
	public List<EstadoDeCuenta> getEstadoContableDeGastos(Persona persona, Date fechaInicio, Date fechaFin);
	public EstadoDeCuenta getEstadoDeCuentaPorIdCuenta(Integer id, Date fechaInicio, Date fechaFin);
}
