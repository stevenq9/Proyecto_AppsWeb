package test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import modelo.EstadoDeCuenta;
import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeGastos;
import modelo.entidades.CuentaDeIngresos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

public class Test {

	public static void main(String[] args) {
		Persona carlos = DAOFactory.getFactory().getPersonaDAO().getById(1);
		
		/*DAOFactory.getFactory().getPersonaDAO().create(carlos);
		
		Cuenta nomina = new CuentaDeIngresos("Nómina", carlos);
		DAOFactory.getFactory().getCuentaDAO().create(nomina);
		
		Cuenta banco = new CuentaDeIngresosYGastos("Banco", carlos);
		((CuentaDeIngresosYGastos)banco).setSaldo(500);
		DAOFactory.getFactory().getCuentaDAO().create(banco);
		
		Cuenta efectivo = new CuentaDeIngresosYGastos("Efectivo", carlos);
		((CuentaDeIngresosYGastos)efectivo).setSaldo(50);
		DAOFactory.getFactory().getCuentaDAO().create(efectivo);
		
		Cuenta universidad = new CuentaDeGastos("Universidad", carlos);
		DAOFactory.getFactory().getCuentaDAO().create(universidad);
		
		Cuenta regalos = new CuentaDeGastos("Regalos", carlos);
		DAOFactory.getFactory().getCuentaDAO().create(regalos);
		
		Movimiento m1 = new Movimiento(Date.valueOf(LocalDate.of(2022, 12, 31)), "Transferencia de nómina de mi trabajo", 1000.0);
		m1.configurarComoIngreso((CuentaDeIngresos)nomina, (CuentaDeIngresosYGastos)banco);
		DAOFactory.getFactory().getMovimientoDAO().create(m1);
		
		Movimiento m2 = new Movimiento(Date.valueOf(LocalDate.of(2023, 1, 16)), "Libro “Contabilidad básica”", 100.0);
		m2.configurarComoGasto((CuentaDeIngresosYGastos)banco, (CuentaDeGastos)universidad);
		DAOFactory.getFactory().getMovimientoDAO().create(m2);
		
		Movimiento m3 = new Movimiento(Date.valueOf(LocalDate.of(2023, 1, 20)), "Traspaso entre cuentas", 400.0);
		m3.configurarComoTransferencia((CuentaDeIngresosYGastos)banco, (CuentaDeIngresosYGastos)efectivo);
		DAOFactory.getFactory().getMovimientoDAO().create(m3);
		
		Movimiento m4 = new Movimiento(Date.valueOf(LocalDate.of(2023, 1, 20)), "Teléfono Celular para mamá", 350.0);
		m4.configurarComoGasto((CuentaDeIngresosYGastos)efectivo, (CuentaDeGastos)regalos);
		DAOFactory.getFactory().getMovimientoDAO().create(m4);*/
		
		/*for(Cuenta c: DAOFactory.getFactory().getCuentaDAO().getCuentasGastosPorPersona(carlos)) {
			System.out.println(c);
		}
		
		for(Cuenta c: DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosPorPersona(carlos)) {
			System.out.println(c);
		}
		
		for(Cuenta c: DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosYGastosPorPersona(carlos)) {
			System.out.println(c);
		}*/
		
		/*for(Movimiento m: DAOFactory.getFactory().getMovimientoDAO().getAll()) {
			System.out.println(m);
		}*/
		
		/*for(Movimiento m: DAOFactory.getFactory().getMovimientoDAO().getMovimientosPorCuenta(banco)) {
			System.out.println(m);
		}*/
		
		//Lógica de un ingreso
		/*CuentaDeIngresos nomina = (CuentaDeIngresos) DAOFactory.getFactory().getCuentaDAO().getById(1);
		CuentaDeIngresosYGastos banco = (CuentaDeIngresosYGastos) DAOFactory.getFactory().getCuentaDAO().getById(2);
		
		Movimiento m = new Movimiento(Date.valueOf(LocalDate.now()), "Holaa", 200.0);
		m.configurarComoIngreso(nomina, banco);
		
		try {
			banco.depositar(m);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		DAOFactory.getFactory().getCuentaDAO().update(banco);
		
		DAOFactory.getFactory().getMovimientoDAO().create(m);*/
		
		//Lógica de un gasto
		/*CuentaDeIngresosYGastos banco = (CuentaDeIngresosYGastos) DAOFactory.getFactory().getCuentaDAO().getById(2);
		CuentaDeGastos universidad = (CuentaDeGastos) DAOFactory.getFactory().getCuentaDAO().getById(4);
		
		Movimiento m = new Movimiento(Date.valueOf(LocalDate.now()), "Lokiar", 20.0);
		m.configurarComoGasto(banco, universidad);
		
		try {
			banco.retirar(m);
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		DAOFactory.getFactory().getCuentaDAO().update(banco);
		
		DAOFactory.getFactory().getMovimientoDAO().create(m);*/
		/*CuentaDeIngresosYGastos banco = (CuentaDeIngresosYGastos) DAOFactory.getFactory().getCuentaDAO().getById(2);
		
		CuentaDeIngresos nomina = (CuentaDeIngresos) DAOFactory.getFactory().getCuentaDAO().getById(1);
		
		List<EstadoDeCuenta> ec = DAOFactory.getFactory().getMovimientoDAO().getEstadoContableDeIngresos(carlos, Date.valueOf(LocalDate.of(2022, 3, 3)), Date.valueOf(LocalDate.of(2023, 02, 02)));
		
		for(EstadoDeCuenta e: ec) {
			System.out.println(e.getCuenta());
			System.out.println(e.getValorTotal());
			System.out.println(e.getFechaInicio());
			System.out.println(e.getFechaFin());
		}
		
		for(EstadoDeCuenta e: DAOFactory.getFactory().getMovimientoDAO().getEstadoContableDeGastos(carlos, Date.valueOf(LocalDate.of(2022, 3, 3)), Date.valueOf(LocalDate.of(2023, 02, 02)))) {
			System.out.println(e.getCuenta());
			System.out.println(e.getValorTotal());
			System.out.println(e.getFechaInicio());
			System.out.println(e.getFechaFin());
		}
		
		for(EstadoDeCuenta e: DAOFactory.getFactory().getMovimientoDAO().getEstadoContableDeIngresosYGastos(carlos, Date.valueOf(LocalDate.of(2022, 3, 3)), Date.valueOf(LocalDate.of(2023, 02, 02)))) {
			System.out.println(e.getCuenta());
			System.out.println(e.getValorTotal());
			System.out.println(e.getFechaInicio());
			System.out.println(e.getFechaFin());
		}*/
	}

}
