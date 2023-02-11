package modelo;

import java.io.Serializable;

public class CuentaDeGastos extends Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	public CuentaDeGastos() {
		super();
	}

	public CuentaDeGastos(int id, String nombre, double saldo, double valorTotal) {
		super(id, nombre, saldo, valorTotal);
	}
	
	@Override
	public void actualizarValorTotal() {
		//		
	}
}
