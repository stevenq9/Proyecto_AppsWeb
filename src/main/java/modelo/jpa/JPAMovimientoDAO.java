package modelo.jpa;

import java.util.List;
import java.sql.Date;

import javax.persistence.Query;

import modelo.EstadoDeCuenta;
import modelo.dao.MovimientoDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO{

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getMovimientosPorCuenta(Cuenta cuenta) {
		String JPQL = "SELECT m FROM Movimiento m WHERE m.cuentaOrigen = :cuenta OR m.cuentaDestino = :cuenta";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("cuenta", cuenta);
		return consulta.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Movimiento> getMovimientosPorCuentaYFechas(Cuenta cuenta, Date fechaInicio, Date fechaFin) {
		String JPQL = "SELECT m FROM Movimiento m WHERE (m.cuentaOrigen = :cuenta OR m.cuentaDestino = :cuenta) AND "
				+ "m.fecha >= :fechaInicio AND m.fecha <= :fechaFin";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("cuenta", cuenta);
		consulta.setParameter("fechaInicio", fechaInicio);
		consulta.setParameter("fechaFin", fechaFin);
		return consulta.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoDeCuenta> getEstadoContableDeIngresos(Persona persona, Date fechaInicio, Date fechaFin) {
		String JPQL = "SELECT new modelo.EstadoDeCuenta(m.cuentaOrigen, SUM(m.cantidad), CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM Movimiento m "
				+ "WHERE m.cuentaOrigen.propietario = :persona "
				+ "AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin "
				+ "AND TYPE(m.cuentaOrigen) = CuentaDeIngresos "
				+ "GROUP BY m.cuentaOrigen";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("persona", persona);
		consulta.setParameter("fechaInicio", fechaInicio);
		consulta.setParameter("fechaFin", fechaFin);
		return consulta.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoDeCuenta> getEstadoContableDeIngresosYGastos(Persona persona, Date fechaInicio, Date fechaFin) {
		String JPQL = "SELECT new modelo.EstadoDeCuenta(c, c.saldo, CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM CuentaDeIngresosYGastos c "
				+ "WHERE c.propietario = :persona ";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("persona", persona);
		consulta.setParameter("fechaInicio", fechaInicio);
		consulta.setParameter("fechaFin", fechaFin);
		return consulta.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoDeCuenta> getEstadoContableDeGastos(Persona persona, Date fechaInicio, Date fechaFin) {
		String JPQL = "SELECT new modelo.EstadoDeCuenta(m.cuentaDestino, SUM(m.cantidad), CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM Movimiento m "
				+ "WHERE m.cuentaDestino.propietario = :persona "
				+ "AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin "
				+ "AND TYPE(m.cuentaDestino) = CuentaDeGastos "
				+ "GROUP BY m.cuentaDestino";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("persona", persona);
		consulta.setParameter("fechaInicio", fechaInicio);
		consulta.setParameter("fechaFin", fechaFin);
		return consulta.getResultList();
	}

}
