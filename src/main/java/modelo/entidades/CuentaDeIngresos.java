package modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class CuentaDeIngresos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeIngresos() {
		super();
	}

	public CuentaDeIngresos(String nombre, Persona propietario) {
		super(nombre, propietario);
	}
}
