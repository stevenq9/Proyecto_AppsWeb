package modelo.dto;

public class CuentaRestDTO {
	
	private Integer id;
	private String nombre;
	private double saldo;
	
	public CuentaRestDTO() {
	}

	public CuentaRestDTO(Integer id, String nombre, double saldo) {
		this.id = id;
		this.nombre = nombre;
		this.saldo = saldo;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
