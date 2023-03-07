package modelo.jpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.EstadoDeCuenta;
import modelo.dao.CuentaDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Persona;

public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	public JPACuentaDAO() {
		super(Cuenta.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> getCuentasIngresosPorPersona(Persona persona) {
		String JPQL = "SELECT c FROM CuentaDeIngresos c WHERE c.propietario = :propietario";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("propietario", persona);
		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> getCuentasIngresosYGastosPorPersona(Persona persona) {
		String JPQL = "SELECT c FROM CuentaDeIngresosYGastos c WHERE c.propietario = :propietario";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("propietario", persona);
		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> getCuentasGastosPorPersona(Persona persona) {
		String JPQL = "SELECT c FROM CuentaDeGastos c WHERE c.propietario = :propietario";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("propietario", persona);
		return consulta.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoDeCuenta> getEstadoContableDeIngresos(Persona persona, Date fechaInicio, Date fechaFin) {
		String JPQL = "SELECT new modelo.EstadoDeCuenta(m.cuentaOrigen, SUM(m.cantidad), CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM Movimiento m "
				+ "WHERE m.cuentaOrigen.propietario = :persona "
				+ "AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin "
				+ "AND TYPE(m.cuentaOrigen) = CuentaDeIngresos " + "GROUP BY m.cuentaOrigen";
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
				+ "AND TYPE(m.cuentaDestino) = CuentaDeGastos " + "GROUP BY m.cuentaDestino";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("persona", persona);
		consulta.setParameter("fechaInicio", fechaInicio);
		consulta.setParameter("fechaFin", fechaFin);
		return consulta.getResultList();
	}

	@Override
	public EstadoDeCuenta getEstadoDeCuentaPorIdCuenta(Integer id, Date fechaInicio, Date fechaFin) {
		String JPQL = "";
		Cuenta cuenta = this.getById(id);
		switch (cuenta.getClass().getSimpleName()) {
		case "CuentaDeIngresos":
			JPQL = "SELECT new modelo.EstadoDeCuenta(m.cuentaOrigen, SUM(m.cantidad), CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM Movimiento m "
					+ "WHERE m.cuentaOrigen = :cuenta "
					+ "AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin "
					+ "AND TYPE(m.cuentaOrigen) = CuentaDeIngresos " + "GROUP BY m.cuentaOrigen";
			break;
		case "CuentaDeGastos":
			JPQL = "SELECT new modelo.EstadoDeCuenta(m.cuentaDestino, SUM(m.cantidad), CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM Movimiento m "
					+ "WHERE m.cuentaDestino = :cuenta "
					+ "AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin "
					+ "AND TYPE(m.cuentaDestino) = CuentaDeGastos "  + "GROUP BY m.cuentaDestino";
			break;
		case "CuentaDeIngresosYGastos":
			JPQL = "SELECT new modelo.EstadoDeCuenta(c, c.saldo, CAST(:fechaInicio AS DATE), CAST(:fechaFin AS DATE)) FROM CuentaDeIngresosYGastos c "
					+ "WHERE c = :cuenta ";
			break;
		}

		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("cuenta", cuenta);
		consulta.setParameter("fechaInicio", fechaInicio);
		consulta.setParameter("fechaFin", fechaFin);
		
		EstadoDeCuenta estadoDeCuenta;
		try {
			estadoDeCuenta = (EstadoDeCuenta) consulta.getSingleResult();
		} catch (NoResultException rse) {
			estadoDeCuenta = null;
		}
		return estadoDeCuenta;
	}

}
