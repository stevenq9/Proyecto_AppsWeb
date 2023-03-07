package modelo.jpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import excepciones.UsuarioYaExisteException;
import modelo.dao.PersonaDAO;
import modelo.entidades.Persona;

public class JPAPersonaDAO extends JPAGenericDAO<Persona, Integer> implements PersonaDAO {

	public JPAPersonaDAO() {
		super(Persona.class);
	}

	@Override
	public void create(Persona entity) {
		if (!this.verificarSiExisteUsuario(entity.getNombreUsuario())) {
			super.create(entity);
		} else {
			throw new UsuarioYaExisteException();
		}
	}

	@Override
	public Persona autorizar(String nombreUsuario, String clave) {
		String JPQL = "SELECT p FROM Persona p WHERE p.clave = :clave AND p.nombreUsuario = :nombreUsuario";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("clave", clave);
		consulta.setParameter("nombreUsuario", nombreUsuario);
		Persona persona;
		try {
			persona = (Persona) consulta.getSingleResult();
		} catch (NoResultException rse) {
			persona = null;
		}
		return persona;
	}

	@Override
	public boolean verificarSiExisteUsuario(String nombreUsuario) {
		String JPQL = "SELECT p FROM Persona p WHERE p.nombreUsuario = :nombreUsuario";
		Query consulta = em.createQuery(JPQL);
		consulta.setParameter("nombreUsuario", nombreUsuario);
		try {
			consulta.getSingleResult();
		} catch (NoResultException rse) {
			return false;
		}
		return true;
	}

}
