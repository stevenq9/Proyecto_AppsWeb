package modelo.dao;

import modelo.entidades.Persona;

public interface PersonaDAO extends GenericDAO<Persona, Integer> {
	public Persona autorizar(String nombreUsuario, String clave);
	public boolean verificarSiExisteUsuario(String nombreUsuario);
}
