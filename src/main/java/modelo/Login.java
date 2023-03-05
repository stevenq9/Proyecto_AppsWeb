package modelo;

import java.util.List;

import modelo.entidades.Persona;

public class Login {
	private static List<Persona> personas;
	
	public Login() {
		
	}
	
	public Persona ingresar(String usuario, String clave) {
		for(Persona persona: personas) {
			if(persona.getNombreUsuario().equals(usuario) && persona.getClave().equals(clave))
				return persona;
		}
		return null;
	}
	
	public void registrar(Persona persona) {
		int max = 0;
		for(Persona p: personas){
			if (max < p.getId()) {
				max = p.getId();
			}
		}
		persona.setId(max + 1);
		personas.add(persona);
	}
	
	public Persona getPersonaPorId(int id) {
		for(Persona persona: personas) {
			if(persona.getId() == id)
				return persona;
		}
		return null;
	}
}
