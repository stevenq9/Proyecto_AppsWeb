package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColeccionDeTransacciones implements Serializable{

	private static final long serialVersionUID = 1L;
	private static List<Transaccion> transacciones;
	private static ColeccionDeTransacciones instancia;
	private static Chaucherita chaucherita;
	
	private ColeccionDeTransacciones() {
		chaucherita = Chaucherita.getInstancia();
		
		transacciones = new ArrayList<Transaccion>();
		
		try {
			transacciones.add(new Transaccion(1, LocalDate.of(2022, 12, 31), chaucherita.getCuentas().get(0), chaucherita.getCuentas().get(1), "Transferencia de nómina de mi trabajo", 1000.0));
			transacciones.add(new Transaccion(2, LocalDate.of(2023, 1, 16), chaucherita.getCuentas().get(1), chaucherita.getCuentas().get(3), "Libro “Contabilidad básica”", 100.0));
			transacciones.add(new Transaccion(3, LocalDate.of(2023, 1, 20), chaucherita.getCuentas().get(1), chaucherita.getCuentas().get(2), "Traspaso entre cuentas", 400.0));
			transacciones.add(new Transaccion(4, LocalDate.of(2023, 1, 20), chaucherita.getCuentas().get(2), chaucherita.getCuentas().get(4), "Teléfono Celular para mamá", 350.0));
		} catch (Exception e) {
			
		}
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
		ColeccionDeTransacciones.chaucherita = chaucherita;
	}

	public static List<Transaccion> getTransaccionesByID(int idCuenta){
		if(chaucherita.obtenerCuentaPorId(idCuenta) == null)
			return null;
		
		List<Transaccion> temp = new ArrayList<>();
		for (Transaccion transaccion : getTransacciones()) {
			if(idCuenta == transaccion.getCuentaOrigen().getId() || idCuenta == transaccion.getCuentaDestino().getId()){
				temp.add(transaccion);
			}
		}
		return temp;
	}
	
	public static List<Transaccion> getTransacciones(LocalDate fechaInicio, LocalDate fechaFin, int idCuenta){
		if(fechaInicio == null || fechaFin == null)
			return null;
		
		if(fechaInicio.isAfter(fechaFin))
			return null;
		
		if(chaucherita.obtenerCuentaPorId(idCuenta) == null) {
			return null;
		}
		
		List<Transaccion> transaccionesDeCuenta = getTransaccionesByID(idCuenta);
		List<Transaccion> transaccionesDeCuentaPorFecha = new ArrayList<>();
		
		for(Transaccion t: transaccionesDeCuenta) {
			if((t.getFecha().isAfter(fechaInicio) || t.getFecha().isEqual(fechaInicio)) && (t.getFecha().isBefore(fechaFin) || t.getFecha().isEqual(fechaFin))) {
				transaccionesDeCuentaPorFecha.add(t);
			}
		}
		
		return transaccionesDeCuentaPorFecha;
	}
	
	public void agregar(Transaccion transaccion) {
		int max=0;
		for(Transaccion t: getTransacciones()) {
			if(max < t.getId()) {
				max=t.getId();
			}
		}
		transaccion.setId(max+1);
		transacciones.add(transaccion);
	}
}
