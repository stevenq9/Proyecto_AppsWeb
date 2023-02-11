package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColeccionDeTransacciones implements Serializable{

	private static final long serialVersionUID = 1L;
	private static List<Transaccion> transacciones;
	private static ColeccionDeTransacciones instancia;
	private Chaucherita chaucherita;
	
	private ColeccionDeTransacciones() {
		this.chaucherita = Chaucherita.getInstancia();
		
		transacciones = new ArrayList<Transaccion>();
		
		transacciones.add(new Transaccion(1, LocalDate.of(2022, 12, 31), chaucherita.getCuentas().get(0), chaucherita.getCuentas().get(1), "Pago de nómina de mi trabajo", 1000.0));
		transacciones.add(new Transaccion(2, LocalDate.of(2023, 1, 16), chaucherita.getCuentas().get(1), chaucherita.getCuentas().get(3), "Libro “Contabilidad básica”", 100.0));
		transacciones.add(new Transaccion(3, LocalDate.of(2023, 1, 20), chaucherita.getCuentas().get(1), chaucherita.getCuentas().get(2), "Traspaso entre cuentas", 400.0));
		transacciones.add(new Transaccion(4, LocalDate.of(2023, 1, 20), chaucherita.getCuentas().get(2), chaucherita.getCuentas().get(4), "Teléfono Celular para mamá", 350.0));
	}

	public static List<Transaccion> getTransacciones() {
		return transacciones;
	}	

	public static ColeccionDeTransacciones getInstancia() {
		if(instancia == null)
			instancia = new ColeccionDeTransacciones();
		return instancia;
	}
	
	public Chaucherita getChaucherita() {
		return chaucherita;
	}

	public void setChaucherita(Chaucherita chaucherita) {
		this.chaucherita = chaucherita;
	}

	public static List<Transaccion> getTransacciones(int idCuenta){
		return null;
	}
	
	public static List<Transaccion> getTransacciones(LocalDate fechaInicio, LocalDate fechaFin, int idCuenta){
		return null;
	}
	
	public void agregar(Transaccion transaccion) {
		transacciones.add(transaccion);
	}
}
