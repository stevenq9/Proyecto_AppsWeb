package modelo.entidades;

import java.io.Serializable;
import java.util.List;

public class CuentaDeGastos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public CuentaDeGastos() {
		super(true, false);
	}

	public CuentaDeGastos(int id, String nombre) {
		super(id, nombre, true, false);
	}
	
	@Override
	public double obtenerValorTotal(List<Movimiento> movimientos) {
		double valorTotal = 0;
		for(Movimiento movimiento: movimientos) {
			if(movimiento.getCuentaDestino() == this)
				valorTotal -= movimiento.getCantidad();
		}
		return valorTotal;
	}
}
