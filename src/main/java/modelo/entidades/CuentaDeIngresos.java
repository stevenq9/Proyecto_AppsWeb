package modelo.entidades;

import java.io.Serializable;
import java.util.List;

public class CuentaDeIngresos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeIngresos() {
		super(false, true);
	}

	public CuentaDeIngresos(int id, String nombre) {
		super(id, nombre, false, true);
	}
	
	@Override
	public double obtenerValorTotal(List<Movimiento> movimientos) {
		double valorTotal = 0;
		for(Movimiento movimiento: movimientos) {			
			if(movimiento.getCuentaOrigen() == this)
				valorTotal += movimiento.getCantidad();
		}
		return valorTotal;
	}
}
