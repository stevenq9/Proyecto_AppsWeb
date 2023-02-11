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
		double valorTotal = 0;
		for(Transaccion transaccion: transacciones) {			
			if(transaccion.getCuentaDestino() == this)
				valorTotal += transaccion.getCantidad();
		}
		return valorTotal;
	}
}
