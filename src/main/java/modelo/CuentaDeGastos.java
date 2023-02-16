package modelo;

import java.io.Serializable;
import java.util.List;

public class CuentaDeGastos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public CuentaDeGastos() {
		super(true);
	}

	public CuentaDeGastos(int id, String nombre) {
		super(id, nombre, true);
	}
	
	@Override
	public double obtenerValorTotal(List<Transaccion> transacciones) {
		double valorTotal = 0;
		for(Transaccion transaccion: transacciones) {
			if(transaccion.getCuentaDestino() == this)
				valorTotal -= transaccion.getCantidad();
		}
		return valorTotal;
	}
}
