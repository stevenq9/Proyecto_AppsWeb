package modelo.dao;

import java.util.List;

import modelo.entidades.Cuenta;
import modelo.entidades.Persona;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer> {
	public List<Cuenta> getCuentasIngresosPorPersona(Persona persona);
	public List<Cuenta> getCuentasIngresosYGastosPorPersona(Persona persona);
	public List<Cuenta> getCuentasGastosPorPersona(Persona persona);
}
