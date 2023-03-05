package modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "nombreUsuario")
	private String nombreUsuario;
	
	@Column(name = "clave")
	private String clave;
	
	public Persona() {
		super();
	}

	public Persona(String apellido, String nombre, String nombreUsuario, String clave) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	@Override
	public String toString() {
		return this.nombre + ' ' + this.apellido + ' ' + this.nombreUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Persona)) {
			return false;
		}
		Persona other = (Persona) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}
}
