package modelo.datos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeGastos;
import modelo.entidades.CuentaDeIngresos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

public class Datos {
	private static List<Persona> personas;
	private static List<Chaucherita> chaucheritas;
	private static List<Cuenta> cuentas;
	private static List<Movimiento> movimientos;
	private static Datos instancia;
	
	
	
	private Datos() {
		CatalogoDeCuentas cc = new CatalogoDeCuentas();
		cc.agregarCuenta(new CuentaDeIngresos(1, "Nomina"));
		cc.agregarCuenta(new CuentaDeIngresosYGastos(2, "Banco"));
		((CuentaDeIngresosYGastos)cc.getCuentas().get(1)).setSaldo(500);
		cc.agregarCuenta(new CuentaDeIngresosYGastos(3, "Efectivo"));
		((CuentaDeIngresosYGastos)cc.getCuentas().get(2)).setSaldo(50);
		cc.agregarCuenta(new CuentaDeGastos(4, "Universidad"));
		cc.agregarCuenta(new CuentaDeGastos(5, "Regalos"));
		cuentas = cc.getCuentas();
		
		CatalogoDeMovimientos cm = new CatalogoDeMovimientos();
		cm.agregarIngreso(LocalDate.of(2022, 12, 31), (CuentaDeIngresos)cc.getCuentas().get(0), (CuentaDeIngresosYGastos)cc.getCuentas().get(1), "Transferencia de nómina de mi trabajo", 1000.0);
		cm.agregarGasto(LocalDate.of(2023, 1, 16), (CuentaDeIngresosYGastos)cc.getCuentas().get(1), (CuentaDeGastos)cc.getCuentas().get(3), "Libro “Contabilidad básica”", 100.0);
		cm.agregarTransferencia(LocalDate.of(2023, 1, 20), (CuentaDeIngresosYGastos)cc.getCuentas().get(1), (CuentaDeIngresosYGastos)cc.getCuentas().get(2), "Traspaso entre cuentas", 400.0);
		cm.agregarGasto(LocalDate.of(2023, 1, 20), (CuentaDeIngresosYGastos)cc.getCuentas().get(2), (CuentaDeGastos)cc.getCuentas().get(4), "Teléfono Celular para mamá", 350.0);
		movimientos = cm.getMovimientos();
		
		chaucheritas = new ArrayList<>();
		Chaucherita c = new Chaucherita();
		c.setCatalogoDeCuentas(cc);
		c.setCatalogoDeMovimientos(cm);
		chaucheritas.add(c);
		
		personas = new ArrayList<>();
		Persona p = new Persona();
		p.setApellido("Iñiguez");
		p.setNombre("Carlos");
		p.setClave("1234");
		p.setId(0);
		p.setNombreUsuario("carlitos");
		p.setChaucherita(c);
		personas.add(p);
	}

	public static Datos getInstancia() {
		if(instancia == null)
			instancia = new Datos();
		return instancia;
	}

	public static List<Persona> getPersonas() {
		return personas;
	}

	public static List<Chaucherita> getChaucheritas() {
		return chaucheritas;
	}

	public static List<Cuenta> getCuentas() {
		return cuentas;
	}

	public static List<Movimiento> getMovimientos() {
		return movimientos;
	}
}
