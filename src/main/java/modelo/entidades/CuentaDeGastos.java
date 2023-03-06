package modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class CuentaDeGastos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public CuentaDeGastos() {
		super();
	}

	public CuentaDeGastos(String nombre, Persona propietario) {
		super(nombre, propietario);
	}
}
