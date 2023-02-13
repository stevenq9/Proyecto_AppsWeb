package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaccion implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate fecha;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private String descripcion;
	private double cantidad;

	public Transaccion() {
	}

	public Transaccion(int id, LocalDate fecha, Cuenta cuentaOrigen, Cuenta cuentaDestino, String descripcion,
			double cantidad) throws Exception{
		super();
		this.id = id;
		this.fecha = fecha;
		
		if((cuentaOrigen != null && !(cuentaOrigen instanceof CuentaConRetiro)))
			throw new Exception("Cuenta de origen no válida");
		this.cuentaOrigen = cuentaOrigen;
		
		if(cuentaDestino == null)
			throw new Exception("Cuenta de destino no válida");
		this.cuentaDestino = cuentaDestino;
		
		this.descripcion = descripcion;
		
		if(cantidad < 0.01)
			throw new Exception("Se ingresó un valor menor a $0.01");
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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

}
