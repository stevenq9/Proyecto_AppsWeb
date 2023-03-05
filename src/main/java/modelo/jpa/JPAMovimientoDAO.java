package modelo.jpa;

import java.time.LocalDate;
import java.util.List;

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
		String JPQL = "SELECT m FROM Movimiento m WHERE m.cuentaOrigen = :cuentaOrigen OR m.cuentaDestino = :cuentaDestino";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("cuentaOrigen", cuenta);
		consulta.setParameter("cuentaDestino", cuenta);
		return consulta.getResultList();
	}

	@Override
	public List<Movimiento> getMovimientosPorCuentaYFechas(Cuenta cuenta, LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}
}
