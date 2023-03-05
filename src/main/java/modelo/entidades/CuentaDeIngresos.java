package modelo.entidades;

import java.io.Serializable;
import java.util.List;

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
