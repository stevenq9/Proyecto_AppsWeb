package modelo;

import java.io.Serializable;
import java.util.List;

public class CuentaDeIngresosYGastos extends CuentaConRetiro implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeIngresosYGastos() {
		super();
	}

	public CuentaDeIngresosYGastos(int id, String nombre) {
		super(id, nombre);
	}
	
	@Override
	public double obtenerValorTotal(List<Transaccion> transacciones) {
		double valorTotal = 0;
		for(Transaccion transaccion: transacciones) {
			if(transaccion.getCuentaOrigen() == this)
				valorTotal -= transaccion.getCantidad();
			
			if(transaccion.getCuentaDestino() == this)
				valorTotal += transaccion.getCantidad();
		}
		return valorTotal;
	}
}
