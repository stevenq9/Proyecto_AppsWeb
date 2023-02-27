package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDeMovimientos implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Movimiento> movimientos;
	
	public CatalogoDeMovimientos() {
		super();
	}

	public void agregarIngreso(int id, LocalDate fecha, CuentaDeIngresos cuentaOrigen,
			CuentaDeIngresosYGastos cuentaDestino, String descripcion, double cantidad) {
		Movimiento ingreso = new Movimiento(fecha, cuentaOrigen, cuentaDestino, descripcion, cantidad, Tipo.INGRESO);
		this.agregarMovimiento(ingreso);
	}

	public void agregarGasto(int id, LocalDate fecha, CuentaDeIngresosYGastos cuentaOrigen,
			CuentaDeGastos cuentaDestino, String descripcion, double cantidad) {
		Movimiento gasto = new Movimiento(fecha, cuentaOrigen, cuentaDestino, descripcion, cantidad, Tipo.GASTO);
		this.agregarMovimiento(gasto);
	}

	public void agregarTransferencia(int id, LocalDate fecha, CuentaDeIngresosYGastos cuentaOrigen,
			CuentaDeIngresosYGastos cuentaDestino, String descripcion, double cantidad) {
		Movimiento transferencia = new Movimiento(fecha, cuentaOrigen, cuentaDestino, descripcion, cantidad,
				Tipo.TRANSFERENCIA);
		this.agregarMovimiento(transferencia);
	}

	private void agregarMovimiento(Movimiento movimiento) {
		movimientos.add(movimiento);
		int max = 0;

		for (Movimiento m : getMovimientos()) {
			if (max < m.getId()) {
				max = m.getId();
			}
		}
		movimiento.setId(max + 1);
		movimientos.add(movimiento);
	}
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public List<Movimiento> getMovimientosByIDCuenta(int idCuenta) {
		List<Movimiento> temp = new ArrayList<>();
		for (Movimiento movimiento : this.movimientos) {
			if(idCuenta == movimiento.getCuentaOrigen().getId() || idCuenta == movimiento.getCuentaDestino().getId()){
				temp.add(movimiento);
			}
		}
		return temp;
	}
	
	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	
	
}
