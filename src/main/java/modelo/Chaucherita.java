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
		
		this.agregar(new CuentaDeIngresos(1, "Nomina"));
		
		this.agregar(new CuentaDeIngresosYGastos(2, "Banco"));
		((CuentaDeIngresosYGastos)cuentas.get(1)).setSaldo(500);
		
		this.agregar(new CuentaDeIngresosYGastos(3, "Efectivo"));
		((CuentaDeIngresosYGastos)cuentas.get(2)).setSaldo(50);
		
		this.agregar(new CuentaDeGastos(4, "Universidad"));
		
		this.agregar(new CuentaDeGastos(5, "Regalos"));		
	}
	
	public static Chaucherita getInstancia() {
		if(instancia == null)
			instancia = new Chaucherita();
		return instancia;
	}
	
	public List<Cuenta> getCuentas(){
		return cuentas;
	}
	
	public List<Cuenta> getCuentas(Class<?> tipo){
		List<Cuenta> cuentasPorTipo = new ArrayList<Cuenta>();
		for(Cuenta c: getCuentas()) {
			if(c.getClass() == tipo) {
				cuentasPorTipo.add(c);
			}
		}
		return cuentasPorTipo;
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
	
	public List<Cuenta> getCuentasDepositables(){
		List<Cuenta> cuentasDepositables = new ArrayList<Cuenta>();
		cuentasDepositables.addAll(this.getCuentasDeIngresosYGastos());
		cuentasDepositables.addAll(this.getCuentasDeGastos());
		return cuentasDepositables;
	}
	
	public Cuenta obtenerCuentaPorId(int id) {
		Cuenta cuentaPorID = null;
		for(Cuenta c: getCuentas()) {
			if(c.getId() == id) {
				cuentaPorID = c;
			}
		}
		return cuentaPorID;
	}
	
	public void agregar(Cuenta cuenta) {
		int max=0;
		for(Cuenta c: getCuentas()) {
			if(max<c.getId()) {
				max=c.getId();
			}
		}
		cuenta.setId(max+1);
		cuentas.add(cuenta);
	}
	
	public void modificar(int id, String Nombre) {
		for(Cuenta c: getCuentas()) {
			if(c.getId()==id) {
				c.setNombre(Nombre);;
			}
		}
	}
}
