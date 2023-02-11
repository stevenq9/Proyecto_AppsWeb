package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chaucherita implements Serializable{

	private static final long serialVersionUID = 1L;
	private static List<Cuenta> cuentas;
	private static Chaucherita instancia;
	
	private Chaucherita() {
		cuentas = new ArrayList<Cuenta>();
	}
	
	public static Chaucherita getInstancia() {
		if(instancia == null)
			instancia = new Chaucherita();
		return instancia;
	}
	
	public List<Cuenta> getCuentas(){
		return cuentas;
	}
	
	public List<Cuenta> getCuentas(Class tipo){
		return null;
	}
	
	public List<Cuenta> getCuentasConRetiro(){
		return this.getCuentas(CuentaConRetiro.class);
	}
	
	public List<Cuenta> getCuentasDeIngresos(){
		return this.getCuentas(CuentaDeIngresos.class);
	}
	
	public List<Cuenta> getCuentasDeIngresosYGastos(){
		return this.getCuentas(CuentaDeIngresosYGastos.class);
	}
	
	public List<Cuenta> getCuentasDeGastos(){
		return this.getCuentas(CuentaDeGastos.class);
	}
	
	public Cuenta obtenerCuentaPorId() {
		return null;
	}
	
	public void agregar(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
}
