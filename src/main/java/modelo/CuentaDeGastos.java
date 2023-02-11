package modelo;

import java.io.Serializable;
import java.util.List;

public class CuentaDeGastos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeGastos() {
		super();
	}

	public CuentaDeGastos(int id, String nombre) {
		super(id, nombre);
	}
	
	@Override
	public double obtenerValorTotal(List<Transaccion> transacciones) {
		return 0;
	}
}
