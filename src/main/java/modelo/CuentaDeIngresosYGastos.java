package modelo;

import java.io.Serializable;

public class CuentaDeIngresosYGastos extends CuentaConRetiro implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeIngresosYGastos() {
		super();
	}

	public CuentaDeIngresosYGastos(int id, String nombre, double saldo, double valorTotal) {
		super(id, nombre, saldo, valorTotal);
	}
	
	@Override
	public void actualizarValorTotal() {
		// TODO Esbozo de método generado automáticamente
		
	}
}
