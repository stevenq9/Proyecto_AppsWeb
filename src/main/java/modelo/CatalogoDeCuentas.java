package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDeCuentas implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Cuenta> cuentas;

	public CatalogoDeCuentas() {
		this.cuentas = new ArrayList<>();
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	private List<Cuenta> getCuentas(Class<?> tipo) {
		List<Cuenta> cuentasPorTipo = new ArrayList<Cuenta>();
		for (Cuenta c : getCuentas()) {
			if (tipo.isInstance(c)) {
				cuentasPorTipo.add(c);
			}
		}
		return cuentasPorTipo;
	}

	public List<Cuenta> getCuentasDeIngresos() {
		return this.getCuentas(CuentaDeIngresos.class);
	}

	public List<Cuenta> getCuentasDeIngresosYGastos() {
		return this.getCuentas(CuentaDeIngresosYGastos.class);
	}

	public List<Cuenta> getCuentasDeGastos() {
		return this.getCuentas(CuentaDeGastos.class);
	}

	public Cuenta obtenerCuentaPorId(int id) {
		Cuenta cuentaPorID = null;
		for (Cuenta c : getCuentas()) {
			if (c.getId() == id) {
				cuentaPorID = c;
			}
		}
		return cuentaPorID;
	}

	public void agregarCuenta(Cuenta cuenta) {
		int max = 0;
		for (Cuenta c : getCuentas()) {
			if (max < c.getId()) {
				max = c.getId();
			}
		}
		cuenta.setId(max + 1);
		cuentas.add(cuenta);
	}

	public void modificar(int id, String Nombre) {
		for (Cuenta c : getCuentas()) {
			if (c.getId() == id) {
				c.setNombre(Nombre);
				;
			}
		}
	}
}
