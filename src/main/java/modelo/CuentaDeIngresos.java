package modelo;

import java.io.Serializable;
import java.util.List;

public class CuentaDeIngresos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeIngresos() {
		super(false);
	}

	public CuentaDeIngresos(int id, String nombre) {
		super(id, nombre, false);
	}
	
	@Override
	public double obtenerValorTotal(List<Transaccion> transacciones) {
		double valorTotal = 0;
		for(Transaccion transaccion: transacciones) {			
			if(transaccion.getCuentaOrigen() == this)
				valorTotal += transaccion.getCantidad();
		}
		return valorTotal;
	}
}
