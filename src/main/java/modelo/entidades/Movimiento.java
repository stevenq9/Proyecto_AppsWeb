package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Movimiento")
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta_origen_fk")
	private Cuenta cuentaOrigen;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta_destino_fk")
	private Cuenta cuentaDestino;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "cantidad")
	private double cantidad;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private Tipo tipo;

	public Movimiento() {
		super();
	}

	public Movimiento(Date fecha, String descripcion, double cantidad) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void configurarComoIngreso(CuentaDeIngresos cuentaDeOrigen, CuentaDeIngresosYGastos cuentaDeDestino) {
		this.cuentaOrigen = cuentaDeOrigen;
		this.cuentaDestino = cuentaDeDestino;
		this.tipo = Tipo.INGRESO;
	}

	public void configurarComoGasto(CuentaDeIngresosYGastos cuentaDeOrigen, CuentaDeGastos cuentaDeDestino) {
		this.cuentaOrigen = cuentaDeOrigen;
		this.cuentaDestino = cuentaDeDestino;
		this.tipo = Tipo.GASTO;
	}

	public void configurarComoTransferencia(CuentaDeIngresosYGastos cuentaDeOrigen,
			CuentaDeIngresosYGastos cuentaDeDestino) {
		this.cuentaOrigen = cuentaDeOrigen;
		this.cuentaDestino = cuentaDeDestino;
		this.tipo = Tipo.TRANSFERENCIA;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Movimiento)) {
			return false;
		}
		Movimiento other = (Movimiento) obj;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "De: " + this.cuentaOrigen.getNombre() + " A: " + this.cuentaDestino.getNombre() + " Monto: $"
				+ this.cantidad + "Descripción: " + this.descripcion;
	}
}
