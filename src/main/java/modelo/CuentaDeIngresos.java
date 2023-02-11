package modelo;

import java.io.Serializable;
import java.util.List;

public class CuentaDeIngresos extends CuentaConRetiro implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeIngresos() {
		super();
	}

	public CuentaDeIngresos(int id, String nombre) {
		super(id, nombre);
	}
	
	@Override
	public double obtenerValorTotal(List<Transaccion> transacciones) {
		return 0;
	}
}
