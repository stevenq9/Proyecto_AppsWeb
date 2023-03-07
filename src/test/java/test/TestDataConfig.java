package test;

import java.sql.Date;
import java.time.LocalDate;
import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeGastos;
import modelo.entidades.CuentaDeIngresos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

public class TestDataConfig {

	public static void main(String[] args) {
		Persona carlos = new Persona("Iñiguez", "Carlos", "ciniguez", "123");

		DAOFactory.getFactory().getPersonaDAO().create(carlos);

		Cuenta nomina = new CuentaDeIngresos("Nómina", carlos);
		DAOFactory.getFactory().getCuentaDAO().create(nomina);

		Cuenta banco = new CuentaDeIngresosYGastos("Banco", carlos);
		((CuentaDeIngresosYGastos) banco).setSaldo(500);
		DAOFactory.getFactory().getCuentaDAO().create(banco);

		Cuenta efectivo = new CuentaDeIngresosYGastos("Efectivo", carlos);
		((CuentaDeIngresosYGastos) efectivo).setSaldo(50);
		DAOFactory.getFactory().getCuentaDAO().create(efectivo);

		Cuenta universidad = new CuentaDeGastos("Universidad", carlos);
		DAOFactory.getFactory().getCuentaDAO().create(universidad);

		Cuenta regalos = new CuentaDeGastos("Regalos", carlos);
		DAOFactory.getFactory().getCuentaDAO().create(regalos);

		Movimiento m1 = new Movimiento(Date.valueOf(LocalDate.of(2022, 12, 31)),
				"Transferencia de nómina de mi trabajo", 1000.0);
		m1.configurarComoIngreso((CuentaDeIngresos) nomina, (CuentaDeIngresosYGastos) banco);
		DAOFactory.getFactory().getMovimientoDAO().create(m1);

		Movimiento m2 = new Movimiento(Date.valueOf(LocalDate.of(2023, 1, 16)), "Libro “Contabilidad básica”", 100.0);
		m2.configurarComoGasto((CuentaDeIngresosYGastos) banco, (CuentaDeGastos) universidad);
		DAOFactory.getFactory().getMovimientoDAO().create(m2);

		Movimiento m3 = new Movimiento(Date.valueOf(LocalDate.of(2023, 1, 20)), "Traspaso entre cuentas", 400.0);
		m3.configurarComoTransferencia((CuentaDeIngresosYGastos) banco, (CuentaDeIngresosYGastos) efectivo);
		DAOFactory.getFactory().getMovimientoDAO().create(m3);

		Movimiento m4 = new Movimiento(Date.valueOf(LocalDate.of(2023, 1, 20)), "Teléfono Celular para mamá", 350.0);
		m4.configurarComoGasto((CuentaDeIngresosYGastos) efectivo, (CuentaDeGastos) regalos);
		DAOFactory.getFactory().getMovimientoDAO().create(m4);
	}

}
