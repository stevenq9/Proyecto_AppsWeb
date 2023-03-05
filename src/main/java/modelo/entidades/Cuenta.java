package modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.Transaccion;

@Entity
@Table(name = "Cuenta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "propietario_id_fk")
	private Persona propietario;
	
	@Column(insertable = false, updatable = false)
	private String dtype;

	public Cuenta() {
		super();
	}

	public Cuenta(String nombre) {
		this();
		this.nombre = nombre;
	}

	protected Cuenta(String nombre, Persona propietario) {
		super();
		this.nombre = nombre;
		this.propietario = propietario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public abstract double obtenerValorTotal(List<Movimiento> movimientos);

	public void procesarTransaccion(Movimiento movimiento) throws Exception {
		if (movimiento.getCuentaOrigen().getId() != this.getId()
				&& movimiento.getCuentaDestino().getId() != this.getId())
			throw new Exception("La cuenta no participa en la transacción");
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cuenta)) {
			return false;
		}
		Cuenta other = (Cuenta) obj;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Nombre cuenta : " + this.getNombre();
	}
}
