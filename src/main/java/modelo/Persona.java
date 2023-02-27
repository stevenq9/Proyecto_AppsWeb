package modelo;

import java.io.Serializable;

public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String clave;
	private Chaucherita chaucherita;
	
	public Persona() {
		super();
	}
	
	public Chaucherita getChaucherita() {
		return chaucherita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setChaucherita(Chaucherita chaucherita) {
		this.chaucherita = chaucherita;
	}
}
