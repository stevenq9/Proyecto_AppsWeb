package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.CuentaDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Persona;

public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO{

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

}
