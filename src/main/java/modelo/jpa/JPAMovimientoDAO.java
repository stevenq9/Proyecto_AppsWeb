package modelo.jpa;

import java.util.List;
import java.sql.Date;

import javax.persistence.Query;

import modelo.dao.MovimientoDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;

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

}
